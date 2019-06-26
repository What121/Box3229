package com.bestom.boxmod.manager;

import android.util.Log;

import com.bestom.boxmod.common.bean.netcfg;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ShellManager {
    private Process process;

    private final String TAG=this.getClass().getSimpleName();

    private static volatile ShellManager instance;

    public static ShellManager getInstance() {
        if (instance == null) {
            synchronized (ShellManager.class) {
                if (instance == null) {
                    instance = new ShellManager();
                }
            }
        }
        return instance;
    }

    public boolean MustComd(String cmd) {
        DataOutputStream dataOutputStream = null;
        BufferedReader resultStream = null;
        try {
            // 申请su权限
            process = Runtime.getRuntime().exec("su root");

            //region 处理process.getInputStream()的线程
            new Thread()
            {
                @Override
                public void run()
                {
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = null;

                    try
                    {
                        while((line = in.readLine()) != null)
                        {
                            System.out.println("output: " + line);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            in.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            //endregion


            //region 处理process.getErrorStream()的线程
            new Thread()
            {
                @Override
                public void run()
                {
                    BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String line = null;

                    try
                    {
                        while((line = err.readLine()) != null)
                        {
                            System.out.println("err: " + line);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            err.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            //endregion

            dataOutputStream = new DataOutputStream(process.getOutputStream());
            // 执行pm install命令
            String command = cmd+"\n";
            Log.i("command",command);
            dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));
            dataOutputStream.flush();
            //process.waitFor();

        } catch (Exception e) {
            Log.e("TAG", e.getMessage(), e);
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                Log.e("TAG", e.getMessage(), e);
            }
        }
        return true;
    }

    public void netcfgeth0(netcfg netcfg){
        //判断是否设置子网掩码
        if (netcfg.getNetmask()!=null&&!netcfg.getNetmask().equals("")){
            //设置ip,子网掩码
            MustComd("ifconfig eth0 "+netcfg.getIp()+" netmask "+netcfg.getNetmask());
        }else {
            //设置ip
            MustComd("ifconfig eth0 "+netcfg.getIp());
        }
        //设置网关
        MustComd("route add default gw "+netcfg.getGetway()+" dev eth0");

        //设置dns
        if (netcfg.getDns1()!=null&&!netcfg.getDns1().equals("")){
            MustComd("setprop net.eth0.dns1 "+netcfg.getDns1());
        }
        if (netcfg.getDns1()!=null&&!netcfg.getDns1().equals("")){
            MustComd("setprop net.eth0.dns2 "+netcfg.getDns2());
        }
        if (netcfg.getDns1()!=null&&!netcfg.getDns1().equals("")){
            MustComd("setprop net.eth0.dns3 "+netcfg.getDns3());
        }
        if (netcfg.getDns1()!=null&&!netcfg.getDns1().equals("")){
            MustComd("setprop net.eth0.dns4 "+netcfg.getDns4());
        }

    }

}
