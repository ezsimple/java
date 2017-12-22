package kr.or.voj.webapp.service.oadr2;

import net.ion.open.oadr2.model.v20b.OadrCreateReport;
import net.ion.open.oadr2.model.v20b.OadrReportRequest;
import net.ion.open.oadr2.model.v20b.OadrSignedObject;
import net.ion.open.oadr2.model.v20b.PulseCountType;
import net.ion.open.oadr2.model.v20b.ei.ObjectFactory;
import net.ion.open.oadr2.model.v20b.ei.ReadingTypeEnumeratedType;
import net.ion.open.oadr2.model.v20b.ei.ReportSpecifier;
import net.ion.open.oadr2.model.v20b.ei.SpecifierPayload;
import net.ion.open.oadr2.model.v20b.xcal.DurationPropType;
import net.ion.open.oadr2.model.v20b.xcal.DurationValue;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Some sanity checking for our JAXB-generated models
 * 
 * @author <a href='mailto:tnichols@enernoc.com'>Thom Nichols</a>
 *
 */
public class OadrSignedObjectTest {

    JAXBContext jaxbContext;
    Marshaller marshaller;
    DatatypeFactory xmlDataTypeFac;

    SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
    Schema schema;
    Validator validator;
    
    ObjectFactory of = new ObjectFactory();
    
    @Before public void setup() throws Exception {
        this.jaxbContext = JAXBContext.newInstance(
                "net.ion.open.oadr2.model.v20b:" +
                "net.ion.open.oadr2.model.v20b.atom:" +
                "net.ion.open.oadr2.model.v20b.currency:" +
                "net.ion.open.oadr2.model.v20b.ei:" +
                "net.ion.open.oadr2.model.v20b.emix:" +
                "net.ion.open.oadr2.model.v20b.gml:" +
                "net.ion.open.oadr2.model.v20b.greenbutton:" +
                "net.ion.open.oadr2.model.v20b.power:" +
                "net.ion.open.oadr2.model.v20b.pyld:" +
                "net.ion.open.oadr2.model.v20b.siscale:" +
                "net.ion.open.oadr2.model.v20b.strm:" +
                "net.ion.open.oadr2.model.v20b.xcal:" +
                "net.ion.open.oadr2.model.v20b.xmldsig:" +
                "net.ion.open.oadr2.model.v20b.xmldsig11" );
        
        this.marshaller = jaxbContext.createMarshaller();
        xmlDataTypeFac = DatatypeFactory.newInstance();

        URL xsd = getClass().getResource("/schema/2.0b/oadr_20b.xsd");


        schema = sf.newSchema( getClass().getResource("/schema/2.0b/oadr_20b.xsd") );
        this.validator = schema.newValidator();
    }
    
    @Test public void testSerialize() throws Exception {
        final Duration duration = xmlDataTypeFac.newDuration( true, 0, 0, 0, 0, 5, 0 );
        
        OadrSignedObject payload = new OadrSignedObject()
            .withOadrCreateReport( new OadrCreateReport()
                .withRequestID( "1234" )
                .withVenID( "Vtn-1234" )
                .withSchemaVersion( "2.0b" )
                .withOadrReportRequests( new OadrReportRequest()
                        .withReportRequestID( "request-1234" )
                        .withReportSpecifier( new ReportSpecifier()
                                .withReportSpecifierID( "1234" )
                                .withGranularity( new DurationPropType( new DurationValue( duration.toString() ) ) )
                                .withReportBackDuration( new DurationPropType( new DurationValue( duration.toString() ) ) )
                                .withSpecifierPayloads( new SpecifierPayload()
                                        .withRID( "report 1234")
                                                .withReadingType( ReadingTypeEnumeratedType.DIRECT_READ.value())
//                                        .withItemBase( of.createPulseCount( new PulseCountType()
//                                                .withItemDescription( "pulse count" )
//                                                .withItemUnits( "count" )
//                                                .withPulseFactor( .01f ) ))
                                ))));

        assertEquals("1234", payload.getOadrCreateReport().getRequestID());
        
        StringWriter out = new StringWriter();
        this.marshaller.marshal(payload, out);
        
        assertNotNull(out.toString());
        
        assertTrue(out.toString().length() > 0);
        
        assertEquals( .01f, ((PulseCountType)payload.getOadrCreateReport()
                .getOadrReportRequests().get(0)
                .getReportSpecifier().getSpecifierPayloads().get(0)
                    .getItemBase().getValue()).getPulseFactor(), 0 );
        
        
        assertEquals( 0, validate(out.toString()) );
    }


    protected int validate( String doc ) throws IOException, SAXException {
        ErrorCollector errorCollector = new ErrorCollector();
        validator.setErrorHandler( errorCollector );
        validator.validate( new StreamSource( new StringReader( doc ) ) );

        return errorCollector.errors.size();
    }
    
    class ErrorCollector extends DefaultHandler {
        List<SAXParseException> errors = new ArrayList<SAXParseException>();
        @Override public void error( SAXParseException e ) throws SAXException {
            System.out.println( "SAX Parse error (" + e.getLineNumber() + "): " + e.getMessage() );
            errors.add( e );
        }
    }
}
