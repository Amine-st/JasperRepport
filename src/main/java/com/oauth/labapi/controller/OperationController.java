/*
package com.oauth.labapi.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.icu.impl.Assert;
import com.oauth.labapi.model.*;
import com.oauth.labapi.utils.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import javax.ws.rs.*;

@RestController
@RequestMapping("api")
public class OperationController {


    @GetMapping("getDay")
    public void getDayPassed() throws ParseException, IOException {
        Long sf = 0L, sm = 0L, tp = 0L;
        List<OperationFacture> operationFactureList = new ArrayList<>();
        List<OperationFacture> operationFactureList2 = new ArrayList<>();
        List<Facture> factureList = new ArrayList<>();
        DayPassedEtat dayPassedEtat = new DayPassedEtat();
       */
/* String CTP_USERS_FILE = "C:\\Users\\chamd\\IdeaProjects\\state machine\\lab-api\\src\\main\\resources\\static\\Cas_Nominal.xlsx";
        operationFactureList = LoadFromXls.getLines(CTP_USERS_FILE, 0).stream().collect(Collectors.toList());

        operationFactureList2 = operationFactureList.stream().sorted(Comparator.comparingLong(OperationFacture::getParent_id)).collect(Collectors.toList());*//*



        final String uri = "https://strange-cat-0.loca.lt/e-facture-api/api/operationByStatut";
        final String uriFacture = "https://strange-cat-0.loca.lt/e-facture-api/api/factures";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OperationFacture[]> response =
                restTemplate.getForEntity(
                        uri,
                        OperationFacture[].class);
        OperationFacture[] operationFactures = response.getBody();

        ResponseEntity<Facture[]> responseF =
                restTemplate.getForEntity(
                        uriFacture,
                        Facture[].class);
        Facture[] factures = responseF.getBody();

        for(Facture facture: factures){
            if(facture.getId()==20)
                factureList.add(facture);

        }

            for(OperationFacture operationFacture: operationFactures){
                if(operationFacture.getFacture_id()==20)
                    operationFactureList.add(operationFacture);

            }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+operationFactureList);
        OperationFacture operationFacture = new OperationFacture();
            for(OperationFacture op : operationFactureList){
                if(op.getParent_opertion_id()==0)
                {
                    operationFacture = op;
                    break;
                }

            }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+operationFacture);
        CheminParentFils cheminParentFils = new CheminParentFils();

        Map<Long, OperationFacture> operationLongMap = cheminParentFils.operationListToMap(operationFactureList);
        operationFactureList2 = cheminParentFils.ParentToFils(operationLongMap, 76,operationFacture);


        String source = "FACTURE_DEPOSEE";
        String target = "FACTURE_VALIDE_TECHNIQUE";
        sf = dayPassedEtat.operationsDayPassed(operationFactureList2, source, target);

        String source1 = "FACTURE_VALIDE_TECHNIQUE";
        String target1 = "DOSSIER_PAIMENT_ORDONNANCE";
        sm = dayPassedEtat.operationsDayPassed(operationFactureList2, source1, target1);

        String source2 = "DOSSIER_PAIMENT_ORDONNANCE";
        String target2 = "DOSSIER_PAYE";
        tp = dayPassedEtat.operationsDayPassed(operationFactureList2, source2, target2);

        Long nombreTotalJours = sf + sm + tp;
        Long nbrJourRetard = 0L;
        if (nombreTotalJours > 91)
            nbrJourRetard = 90 - nombreTotalJours;

        if (sf > 30)
            sm += 30 - sf;

        System.out.println("indicateur service fait : " + sf);

        System.out.println("indicateur emession : " + sm);

        System.out.println("indicateur paiement : " + tp);

        Model poModel = new Model();
        PModel pModel = new PModel();
        List<Model> models = new ArrayList<>();


        poModel.setFactureId(operationFactureList2.get(0).getFacture_id());
        poModel.setNbrTotalDeJours(nombreTotalJours);
        poModel.setNbrDeJoursRetard(Math.abs(nbrJourRetard));
        poModel.setIndicateurSf(sf);
        poModel.setIndicateurEm(sm);
        poModel.setIndicateurTp(tp);
        poModel.setMontant(factureList.get(0).getMontant());
        poModel.setDateDepot(operationFactureList2.get(0).getDate_effet());
        poModel.setDatePvRecep(factureList.get(0).getDatePvRecep());
        poModel.setNumero(factureList.get(0).getNumero());
        poModel.setPrestataire(factureList.get(0).getPrestataire());
        poModel.setReference(factureList.get(0).getContrat());
        models.add(poModel);models.add(poModel);models.add(poModel);

        pModel.setModels(models);
        pModel.setPrestataire(factureList.get(0).getPrestataire());
        pModel.setPrestataireId(55);
        File f = JasperCompilerManager.generateregulationSlaireMAD(pModel);
       // SendMail.sendemail("c.hamdaoui98@gmail.com", "hello world", f);


    }
    @GetMapping("getpdf")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestBody String pathFile) throws IOException {
        pathFile = "C:\\Users\\AMINE-ALAOUI\\Desktop\\api_lab\\JasperRepport\\test10.pdf";
        System.out.println(pathFile);
        InputStreamResource resource = new InputStreamResource(new FileInputStream( pathFile));
        File fileToDownload = resource.getFile();


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileToDownload.getName())
                .contentLength(fileToDownload.length())
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(resource);
    }

    public static void main(String[] args) throws IOException, ParseException {
        OperationController o = new OperationController();
        o.getDayPassed();
    }
}
*/
