/* This project is licensed under the Mulan PSL v2.
 You can use this software according to the terms and conditions of the Mulan PSL v2.
 You may obtain a copy of Mulan PSL v2 at:
     http://license.coscl.org.cn/MulanPSL2
 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 PURPOSE.
 See the Mulan PSL v2 for more details.
 Create: 2023
*/

package com.datastat.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(CsvFileUtil.class);
    public static List<HashMap<String, Object>> readFile(String file) {
        try {
            BufferedReader textFile = new BufferedReader(new FileReader(new File(file)));
            String lineDta;
            int lineNum = 0;
            String[] header = null;
            ArrayList<HashMap<String, Object>> res = new ArrayList<>();
            while ((lineDta = textFile.readLine()) != null) {
                if (lineNum == 0) {
                    header = lineDta.split(",");
                } else {
                    HashMap<String, Object> dataMap = new HashMap<>();
                    String[] datas = lineDta.split(",");
                    for (int i = 0; i < header.length; i++) {
                        dataMap.put(header[i], datas[i]);
                    }
                    res.add(dataMap);
                }
                lineNum++;
            }

            return res;

        } catch (Exception e) {
            logger.error("exception", e);
            return null;
        }
    }
}
