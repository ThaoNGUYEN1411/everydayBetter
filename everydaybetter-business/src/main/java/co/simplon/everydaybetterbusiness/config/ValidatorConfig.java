//package co.simplon.everydaybetterbusiness.config;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
//
//@Configuration
//public class ValidatorConfig {
//
//    @Bean
//    public LocalValidatorFactoryBean validatorFactory(ApplicationContext context) {
//        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
//        factory.setConstraintValidatorFactory(
//                new SpringConstraintValidatorFactory(context.getAutowireCapableBeanFactory()));
//        return factory;
//    }
//}
