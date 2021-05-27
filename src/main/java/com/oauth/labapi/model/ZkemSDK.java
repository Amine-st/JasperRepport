package com.oauth.labapi.model;

import com.jacob.activeX.ActiveXComponent;

/**
 * @ClassName:${type_name}
 * @Description: $ {todo} (attendance connection test)
 * @author: ZHOUPAN
 * @date ${date} ${time}
 * @Copyright: 2018 www.zsplat.com Inc. All rights reserved.
 * ${tags}
 */

public class ZkemSDK {

    private static ActiveXComponent zkem = new ActiveXComponent("MA300");

    /**
     * Attendance link
     *
     * @Param address attendance address
     * @Param port the port number
     * @return
     */
    public boolean connect(String address, int port) {
        boolean result = zkem.invoke("Connect_NET", address, port).getBoolean();
        return result;
    }

    /**
     * Attendance disconnect link
     */
    public void disConnect() {
        zkem.invoke("Disconnect");
    }

    public static void main(String[] args) {
        ZkemSDK sdk = new ZkemSDK();
        boolean  connFlag = sdk.connect("192.168.1.202", 4370);
        System.out.println("conn:"+connFlag);
    }
}
