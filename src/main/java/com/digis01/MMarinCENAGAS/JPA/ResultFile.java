
package com.digis01.MMarinCENAGAS.JPA;

import java.util.List;


public class ResultFile {
     public String pathFile;
     public List<ErrorFile> errorsFile;
     public ResultFile(){
     }
    public ResultFile(String pathFile, List<ErrorFile> errorsFile) {
        this.pathFile = pathFile;
        this.errorsFile = errorsFile;
    }
     
}
