/*


package com.oauth.labapi.config;


import com.oauth.labapi.model.Facture;
import com.oauth.labapi.model.Operation;
import com.oauth.labapi.model.OperationFacture;
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
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<States, EventsF> {
    String CTP_USERS_FILE = "C:\\Users\\chamd\\IdeaProjects\\state machine\\lab-api\\src\\main\\resources\\static\\Cas_Nominal.xlsx";

    List<OperationFacture> oprs;

    Map<Map<String,String>,Long> map=new HashMap<>();
    long sf=0L;
    long sm=0L;
    long tp=0L;

    {
        try {
            oprs = LoadFromXls.getLines(CTP_USERS_FILE,0 ).stream().filter(operation -> operation.getId()==1).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    ;

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, EventsF> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, EventsF> states)
            throws Exception {
        states
                .withStates()
                .initial(States.INITIAL)
                .states(EnumSet.allOf(States.class));


    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, EventsF> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.INITIAL)
                .target(States.FACTURE_DEPOSEE)
                .event(EventsF.DEPOT_FACTURE)
                .and()
                .withExternal()
                .source(States.FACTURE_DEPOSEE)
                .target(States.FACTURE_TECHNIQUE_REJETEE)
                .event(EventsF.ST_REJET_DU_SERVICE_FAIT).action(action1())
                .and()
                .withExternal()
                .source(States.FACTURE_DEPOSEE)
                .target(States.FACTURE_VALIDE_TECHNIQUE)
                .event(EventsF.ST_VALIDATION_DU_SERVICE_FAIT).action(action1())
                .and()
                .withExternal()
                .source(States.FACTURE_SATISFAITE)
                .target(States.FACTURE_VALIDE_TECHNIQUE)
                .event(EventsF.ST_SATISFACTION_DU_SERVICE_FAIT).action(action1())
                .and()
                .withExternal()
                .source(States.FACTURE_SATISFAITE)
                .target(States.FACTURE_TECHNIQUE_REJETEE)
                .event(EventsF.ST_REJET_DU_SERVICE_FAIT).action(action1())
                .and()
                .withExternal()
                .source(States.FACTURE_TECHNIQUE_REJETEE)
                .target(States.FACTURE_SATISFAITE)
                .event(EventsF.ST_SATISFACTION_DU_SERVICE_FAIT).action(action1())
                .and()
                .withExternal()
                .source(States.FACTURE_VALIDE_TECHNIQUE)
                .target(States.DOSSIER_PAIMENT_EMIS)
                .event(EventsF.SM_EMISSION_DE_DOSSIER_DE_PAIEMENT).action(action1())
                .and()
                .withExternal()
                .source(States.FACTURE_VALIDE_TECHNIQUE)
                .target(States.DOSSIER_PAIMENT_REJETE_TECH)
                .event(EventsF.REJET_DOSSIER_DE_PAIEMENT_TECHNIQUE).action(action1())
                .and()
                .withExternal()
                .source(States.FACTURE_VALIDE_TECHNIQUE)
                .target(States.DOSSIER_PAIMENT_REJETE_FRNS)
                .event(EventsF.REJET_DOSSIER_DE_PAIEMENT_FOURNISSEUR).action(action1())
                .and()
                .withExternal()
                .source(States.DOSSIER_PAIMENT_REJETE_TECH)
                .target(States.FACTURE_VALIDE_TECHNIQUE)
                .event(EventsF.ST_SATISFACTION_DU_REJET_DU_DOSSIER_DE_PAIEMENT).action(action1())
                .and()
                .withExternal()
                .source(States.DOSSIER_PAIMENT_REJETE_FRNS)
                .target(States.DOSSIER_PAIMENT_SATISFAIT)
                .event(EventsF.FRNS_SATISFACTION_DU_REJET_DU_DOSSIER_DE_PAIEMENT).action(action1())
                .and()
                .withExternal()
                .source(States.DOSSIER_PAIMENT_SATISFAIT)
                .target(States.DOSSIER_PAIMENT_EMIS)
                .event(EventsF.SM_EMISSION_DE_DOSSIER_DE_PAIEMENT).action(action1())
                .and()
                .withExternal()
                .source(States.DOSSIER_PAIMENT_SATISFAIT)
                .target(States.DOSSIER_PAIMENT_REJETE_FRNS)
                .event(EventsF.REJET_DOSSIER_DE_PAIEMENT_FOURNISSEUR).action(action1())
                .and()
                .withExternal()
                .source(States.DOSSIER_PAIMENT_EMIS)
                .target(States.DOSSIER_PAIMENT_ORDONNANCE)
                .event(EventsF.ORDONNANCEMENT_DU_DOSSIER_DE_PAIEMENT).action(action1())
                 .and()
                .withExternal()
                .source(States.DOSSIER_PAIMENT_EMIS)
                .target(States.ORDONNANCEMENT_REJETE)
                .event(EventsF.REJET_DE_ORDONNANCMENT).action(action1())
                .and()
                .withExternal()
                .source(States.ORDONNANCEMENT_REJETE)
                .target(States.SATISFACTION_ORDONNANCEMENT)
                .event(EventsF.SATISFACTION_DU_REJET_DE_ORDONNANCEMENT).action(action1())
                .and()
                .withExternal()
                .source(States.SATISFACTION_ORDONNANCEMENT)
                .target(States.DOSSIER_PAIMENT_EMIS)
                .event(EventsF.SM_EMISSION_DE_DOSSIER_DE_PAIEMENT).action(action1())
                .and()
                .withExternal()
                .source(States.SATISFACTION_ORDONNANCEMENT)
                .target(States.DOSSIER_PAIMENT_REJETE_TECH)
                .event(EventsF.REJET_DOSSIER_DE_PAIEMENT_TECHNIQUE).action(action1())
                .and()
                .withExternal()
                .source(States.SATISFACTION_ORDONNANCEMENT)
                .target(States.DOSSIER_PAIMENT_REJETE_FRNS)
                .event(EventsF.REJET_DOSSIER_DE_PAIEMENT_FOURNISSEUR).action(action1())
                .and()
                .withExternal()
                .source(States.DOSSIER_PAIMENT_ORDONNANCE)
                .target(States.DOSSIER_PAYE)
                .event(EventsF.PAIEMENT).action(action1())
                .and()
                .withExternal()
                .source(States.DOSSIER_PAIMENT_ORDONNANCE)
                .target(States.PAIEMENT_REJETE)
                .event(EventsF.REJET_DE_PAIEMENT).action(action1())
                .and()
                .withExternal()
                .source(States.PAIEMENT_REJETE)
                .target(States.DOSSIER_PAIMENT_ORDONNANCE)
                .event(EventsF.SATISFACTION_REJET_DE_PAIEMENT).action(action1())
                .and()
                .withExternal()
                .source(States.PAIEMENT_REJETE)
                .target(States.ORDONNANCEMENT_REJETE)
                .event(EventsF.REJET_DE_ORDONNANCMENT).action(action1())
                .and()
                .withExternal()
                .source(States.FACTURE_SATISFAITE)
                .target(States.FACTURE_VALIDE_TECHNIQUE)
                .event(EventsF.ST_VALIDATION_DU_SERVICE_FAIT).action(action1());




    }

    @Bean
    public StateMachineListener<States, EventsF> listener() {
        return new StateMachineListenerAdapter<States, EventsF>() {
            @Override
            public void stateChanged(State<States, EventsF> from, State<States, EventsF> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }


    @Bean
    public Action<States, EventsF> action0() {
        return new Action<States, EventsF>() {

            @SneakyThrows
            @Override
            public void execute(StateContext<States, EventsF> context) {
                String CTP_USERS_FILE = "C:\\Users\\chamd\\IdeaProjects\\state machine\\lab-api\\src\\main\\resources\\static\\Cas_Nominal.xlsx";
                oprs =  LoadFromXls.getLines(CTP_USERS_FILE,0 ).stream().filter(operation -> operation.getId()==1).collect(Collectors.toList());
            }
        };
    }

    @Bean
    public Action<States, EventsF> action1() {
        return new Action<States, EventsF>() {

            @SneakyThrows
            @Override
            public void execute(StateContext<States, EventsF> context) {



                String source = String.valueOf(context.getSource().getId());
                String target = String.valueOf(context.getTarget().getId());
                String event = String.valueOf(context.getEvent());

                DayPassedEtat d = new DayPassedEtat();
                long da = d.operationsDayPassed3(oprs,source,target);
                Map<String,String> map1 =new HashMap<>();
                map1.put(source,event);
                map.put(map1,da);
                System.out.println(map);

                if(target.compareTo("FACTURE_TECHNIQUE_REJETEE")==0){

                    sf+=da;
                    System.out.println(sf);

                }


            }
        };
    }



   */
/* @Bean
    public Action<States, EventsF> action2() {
        return new Action<States, EventsF>() {

            @SneakyThrows
            @Override
            public void execute(StateContext<States, EventsF> context) {
                OperationTest o = new OperationTest();
                Facture facture = new Facture();



                Facture fac = new Facture();
                fac.setId((long) 1);
                fac.setOperations(oprs);

                String source = String.valueOf(context.getSource().getId());
                String target = String.valueOf(context.getTarget().getId());
                String event = String.valueOf(context.getEvent());

                DayPassedEtat d = new DayPassedEtat();
                System.out.println(source+"===================================== : "+event);


                long da = d.operationsDayPassed2(oprs,event);
                System.out.println(da);

                if(da!=0)
                map.put(event+""+i++,da);
                System.out.println(map);

            }
        };
    }
*//*

    */
/*@Bean
    public Action<States, EventsF> action() {
        return new Action<States, EventsF>() {

            @SneakyThrows
            @Override
            public void execute(StateContext<States, EventsF> context) {
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
    }*//*

}

*/
