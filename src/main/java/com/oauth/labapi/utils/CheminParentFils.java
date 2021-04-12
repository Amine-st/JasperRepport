package com.oauth.labapi.utils;

import com.oauth.labapi.model.OperationFacture;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheminParentFils {


    public List<OperationFacture> ParentToFils(Map<Long, OperationFacture> operationLongMap, long current_opertion_id,OperationFacture firstOperation) {
        Long temp = firstOperation.getId();

        List<OperationFacture> operationFactureList = new ArrayList<>();

        operationFactureList.add(firstOperation);
        boolean end = false;
        while (!end) {
            OperationFacture res = operationLongMap.get(temp);
            System.out.println(res);
            temp = res.getId();
            operationFactureList.add(res);
            if (current_opertion_id == res.getId())
                end = true;
        }
        return operationFactureList;
    }


   public Map<Long, OperationFacture> operationListToMap(List<OperationFacture> operations) {
        Map<Long, OperationFacture> map =
                operations.stream().collect(Collectors.toMap(OperationFacture::getParent_opertion_id, item -> item));
        return map;
    }
}
