package net.ion.system;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
@EnableElasticsearchRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ElasticsearchCrudRepository.class))
public class ElasticsearchConfiguration implements DisposableBean {

    private static Log logger = LogFactory.getLog(ElasticsearchConfiguration.class);

    @Autowired 
    private ElasticsearchProperties properties;

    private NodeClient client;

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(esClient());
    }

	@Bean
	public Client esClient() {
		/* 주의 : 버전은 2.4.4 로 통일되어야 한다. */
		/* Embedding ElasticSearch 사용시 */
		/*
		final String clusterName = properties.getClusterName();

		Map<String, String> props = properties.getProperties();
		assertThat(props, is(notNullValue()));

		Settings.Builder settingsBuilder = Settings.settingsBuilder();
 		for(String key : props.keySet()) {
			settingsBuilder.put(key, props.get(key));
		}

		Settings settings = settingsBuilder.build();
		return	NodeBuilder
				.nodeBuilder()
				.settings(settings)
				.clusterName(clusterName)
				.node()
				.client();
		*/	
		/* Remote ElasticSearch 사용시 */
        Client client = null;
		try {
			client = TransportClient.builder().build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        return client;
	}

    @Override
    public void destroy() throws Exception {
        if (this.client != null) {
            try {
                if (logger.isInfoEnabled()) {
                    logger.info("Closing Elasticsearch client");
                }
                if (this.client != null) {
                    this.client.close();
                }
            }
            catch (final Exception ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Error closing Elasticsearch client: ", ex);
                }
            }
        }
    }
}