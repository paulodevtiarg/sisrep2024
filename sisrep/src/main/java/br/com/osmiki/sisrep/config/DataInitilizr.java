/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Leonardo
 */
@Component
public class DataInitilizr implements ApplicationListener<ContextRefreshedEvent>{
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        System.out.println("DATA INITILIZR");
       
    }
    
}
