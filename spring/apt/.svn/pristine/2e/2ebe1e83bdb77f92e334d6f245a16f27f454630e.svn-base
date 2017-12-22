package kr.or.voj.webapp.utils;

import net.ion.oadr2.service.impl.Lookup;
import net.ion.open.oadr2.model.v20b.OadrPayload;
import net.ion.open.oadr2.model.v20b.OadrSignedObject;
import net.ion.open.oadr2.xmpp.v20b.JAXBManager;
import org.thymeleaf.util.StringUtils;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015-11-11.
 */
public class OadrUtils {

    public static String marshal(Object payload) throws Exception {
        if(payload == null)
            throw new Exception("payload is null");
        StringWriter sw = new StringWriter();
        Marshaller marshaller = Lookup.getMarshaller(payload);
        marshaller.marshal(payload, sw);
        String xml = XmlUtil.format(sw.toString());
        return xml;
    }

    public static Object unmarshal(final String xml) throws Exception {
        if(StringUtils.isEmpty(xml))
            throw new Exception("xml is empty or null");
        JAXBManager jaxbManager20b = new net.ion.open.oadr2.xmpp.v20b.JAXBManager();
        Unmarshaller unmarshaller20b = jaxbManager20b.getContext().createUnmarshaller();
        Object payload = unmarshaller20b.unmarshal(new StringReader(xml));
        return payload;
    }

    public static Object unwrap(Object payload){
        if (payload==null) {
            throw new RuntimeException("payload is null");
        }

        if(payload instanceof OadrPayload){
            payload =((OadrPayload)payload).getOadrSignedObject();
        }

        if(payload instanceof OadrSignedObject){
            OadrSignedObject oadrSignedObject = (OadrSignedObject) payload;

            for (Method method : OadrSignedObject.class.getMethods()) {
                method.setAccessible(true);
                if(method.getName().startsWith("get") && method.getReturnType()!=Void.class && method.getParameterTypes().length==0){
                    Object object;
                    try {
                        object = method.invoke(oadrSignedObject);
                        if(object!=null){
                            return object;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("OadrPayload UnWrapperException",e);
                    }

                }
            }
        }
        return payload;
    }
}
