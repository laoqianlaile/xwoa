package com.centit.sys.service;

import java.util.ArrayList;
import java.util.List;

import com.centit.support.utils.Algorithm;
import com.centit.sys.po.FDatadictionary;

public class CodeRepositoryUtilExtend extends CodeRepositoryUtil {
    public static final Integer MAXXZRANK = 100000;

    
    public static final List<FDatadictionary> getTreeDictionaryStartBy(String sCatalog,String extraCode,Boolean cascade) {
        List<FDatadictionary> dictionary = CodeRepositoryManager.REPOSITORIES.get(sCatalog);
        
        List<FDatadictionary> retDictionary = new ArrayList<FDatadictionary>();
        //只获得直接下层条目
        if(!cascade){
            for( FDatadictionary dt : dictionary ){
                if(extraCode.equals( dt.getExtracode()))
                    retDictionary.add(dt);
            }
            return retDictionary;
        }
        //获得所有层级子条目包括本身
        retDictionary.addAll(dictionary);   
        int nSorted = -1;
        int n = retDictionary.size();
        for(int i=0;i<n;i++){
            if(extraCode.equals( retDictionary.get(i).getDatacode()) ){
                Algorithm.changeListItem(retDictionary,0,i);
                nSorted = sortAsTreePiece( retDictionary , 0);
                break;
            }
        }
        //nSorted ++;
        for(int i=n-1;i>nSorted;i--)
            retDictionary.remove(i);
        return retDictionary;
    }
    
    private static int sortAsTreePiece(List<FDatadictionary> list, int nCurPos) {
        int pp= nCurPos;
        int n = list.size();
        int sortedInd = pp;
        int i=sortedInd+1;
        while(i<n){
            if(list.get(pp).getDatacode().equals(list.get(i).getExtracode())){
                sortedInd ++;
                Algorithm.changeListItem(list,sortedInd,i);
                sortedInd = sortAsTreePiece(list,sortedInd);
                i = sortedInd;
            }
            i ++;
        }
       return sortedInd;
    }
    
}
