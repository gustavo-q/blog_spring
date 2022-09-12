package cn.keovi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;

@Configuration
public class CosConfig {

    private String secretId ="AKIDpUv68uQgiX8WhfNq44qNfiYBZkhT2NUN";
    private String secretKey = "iANMXS7WhdLp56tSmrhIemhOr43nIZcQ";
    private String regionName = "k1-1300549046";
    private  String region1 = "ap-shanghai";

    @Bean(destroyMethod="shutdown")
    public COSClient cosClientInit() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(region1);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.http);
        return new COSClient(cred, clientConfig);
    }
}
