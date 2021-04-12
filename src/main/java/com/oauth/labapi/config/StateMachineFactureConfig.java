/*


package com.oauth.labapi.config;


import com.oauth.labapi.model.Facture;
import com.oauth.labapi.model.Operation;
import com.oauth.labapi.model.POModel;
import com.oauth.labapi.utils.*;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Configuration
public class StateMachineFactureConfig
        extends EnumStateMachineConfigurerAdapter<StatesFacture, Events> {


    @Override
    public void configure(StateMachineConfigurationConfigurer<StatesFacture, Events> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<StatesFacture, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(StatesFacture.INIT)
                .states(EnumSet.allOf(StatesFacture.class));


    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StatesFacture, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(StatesFacture.INIT).target(StatesFacture.fournisseur).event(Events.E1)
                .and()
                .withExternal()
                .source(StatesFacture.fournisseur).target(StatesFacture.st).action(action1())
                .and()
                .withExternal()
                .source(StatesFacture.st).target(StatesFacture.sm).action(action1())
                .and()
                .withExternal()
                .source(StatesFacture.sm).target(StatesFacture.sor).action(action1())
                .and()
                .withExternal()
                .source(StatesFacture.sor).target(StatesFacture.tp).action(action1())
                .and()
                .withExternal()
                .source(StatesFacture.tp).target(StatesFacture.END).event(Events.E3);




    }

    @Bean
    public StateMachineListener<StatesFacture, Events> listener() {
        return new StateMachineListenerAdapter<StatesFacture, Events>() {
            @Override
            public void stateChanged(State<StatesFacture, Events> from, State<StatesFacture, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }

    @Bean
    public Action<StatesFacture, Events> action1() {
        return new Action<StatesFacture, Events>() {

            @SneakyThrows
            @Override
            public void execute(StateContext<StatesFacture, Events> context) {
                String CTP_USERS_FILE = "C:\\Users\\chamd\\IdeaProjects\\state machine\\lab-api\\src\\main\\resources\\static\\Cas_Nominal.xlsx";
                OperationTest o = new OperationTest();
                Facture facture = new Facture();
                List<Operation> oprs =  LoadFromXls.getLines(CTP_USERS_FILE,0 ).stream().filter(operation -> operation.getId()==1).collect(Collectors.toList());


                Facture fac = new Facture();
                fac.setId((long) 1);
                fac.setOperations(oprs);


                String source = String.valueOf(context.getSource().getId());
                String target = String.valueOf(context.getTarget().getId());

                DayPassedEtat d = new DayPassedEtat();
                System.out.println(source+"===================================== : "+target);
               long da = d.facturesDayPassed(fac,source,target);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :"+da);

            }
        };
    }

    @Bean
    public Action<StatesFacture, Events> action() {
        return new Action<StatesFacture, Events>() {

            @SneakyThrows
            @Override
            public void execute(StateContext<StatesFacture, Events> context) {
                String CTP_USERS_FILE = "C:\\Users\\chamd\\IdeaProjects\\state machine\\lab-api\\src\\main\\resources\\static\\Cas_Nominal.xlsx";
                OperationTest o = new OperationTest();
                Facture facture = new Facture();
                List<Operation> oprs =  LoadFromXls.getLines(CTP_USERS_FILE,0 ).stream().filter(operation -> operation.getId()==1).collect(Collectors.toList());


                Facture fac = new Facture();
                fac.setId((long) 1);
                fac.setOperations(oprs);




                DayPassedEtat d = new DayPassedEtat();
                System.out.println("===================================== : "+context.getTarget().getId());
                long da = d.facturesDayPassed(fac,"SERVICE_Tech","END");
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :"+da);
                POModel poModel = new POModel(fac.getId(),fac.getLibelle(),fac.getMontant(),fac.getReference(),fac.getPrestataire(),fac.getDateDepot(),fac.getDateDepot(),(int)da,fac.getMontant());
                JasperCompilerManager jasperCompilerManager = new JasperCompilerManager();

                    HttpServletResponse response = new HttpServletResponse() {
                        @Override
                        public void addCookie(Cookie cookie) {

                        }

                        @Override
                        public boolean containsHeader(String s) {
                            return false;
                        }

                        @Override
                        public String encodeURL(String s) {
                            return null;
                        }

                        @Override
                        public String encodeRedirectURL(String s) {
                            return null;
                        }

                        @Override
                        public String encodeUrl(String s) {
                            return null;
                        }

                        @Override
                        public String encodeRedirectUrl(String s) {
                            return null;
                        }

                        @Override
                        public void sendError(int i, String s) throws IOException {

                        }

                        @Override
                        public void sendError(int i) throws IOException {

                        }

                        @Override
                        public void sendRedirect(String s) throws IOException {

                        }

                        @Override
                        public void setDateHeader(String s, long l) {

                        }

                        @Override
                        public void addDateHeader(String s, long l) {

                        }

                        @Override
                        public void setHeader(String s, String s1) {

                        }

                        @Override
                        public void addHeader(String s, String s1) {

                        }

                        @Override
                        public void setIntHeader(String s, int i) {

                        }

                        @Override
                        public void addIntHeader(String s, int i) {

                        }

                        @Override
                        public void setStatus(int i) {

                        }

                        @Override
                        public void setStatus(int i, String s) {

                        }

                        @Override
                        public int getStatus() {
                            return 0;
                        }

                        @Override
                        public String getHeader(String s) {
                            return null;
                        }

                        @Override
                        public Collection<String> getHeaders(String s) {
                            return null;
                        }

                        @Override
                        public Collection<String> getHeaderNames() {
                            return null;
                        }

                        @Override
                        public String getCharacterEncoding() {
                            return null;
                        }

                        @Override
                        public String getContentType() {
                            return null;
                        }

                        @Override
                        public ServletOutputStream getOutputStream() throws IOException {
                            return null;
                        }

                        @Override
                        public PrintWriter getWriter() throws IOException {
                            return null;
                        }

                        @Override
                        public void setCharacterEncoding(String s) {

                        }

                        @Override
                        public void setContentLength(int i) {

                        }

                        @Override
                        public void setContentLengthLong(long l) {

                        }

                        @Override
                        public void setContentType(String s) {

                        }

                        @Override
                        public void setBufferSize(int i) {

                        }

                        @Override
                        public int getBufferSize() {
                            return 0;
                        }

                        @Override
                        public void flushBuffer() throws IOException {

                        }

                        @Override
                        public void resetBuffer() {

                        }

                        @Override
                        public boolean isCommitted() {
                            return false;
                        }

                        @Override
                        public void reset() {

                        }

                        @Override
                        public void setLocale(Locale locale) {

                        }

                        @Override
                        public Locale getLocale() {
                            return null;
                        }
                    };
                    response.setContentType("application/x-pdf");
                    response.setHeader("Content-disposition", "inline; filename=po_MG_Sec_mut.pdf");

               File file = jasperCompilerManager.generateregulationSlaireMAD(poModel,Locale.CANADA,response);
               SendMail sendMail = new SendMail();

                sendMail.sendemail("mohamed.ellaouzi@gmail.com","hello world",file);

            }
        };
    }
}

*/
