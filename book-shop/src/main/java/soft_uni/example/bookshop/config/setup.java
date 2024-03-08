package soft_uni.example.bookshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class setup {
    @Bean
    public ModelMapper modelMapper (){
        return new ModelMapper();
    }

}
