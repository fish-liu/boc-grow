package com.grow.demo.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 平台能够管理的服务枚举
 * @author liuxw
 * @version 1.0
 */
public enum ServiceEnum {

    //页面显示，服务器上真实的名字，包含的组件
    YARN("YARN","yarn",ComponentEnum.YARN.class),
    HDFS("HDFS","hadoop",ComponentEnum.HDFS.class),
    HBASE("HBASE","hbase",ComponentEnum.HBASE.class);

    String name;
    //执行脚本需要的名字，
    String scriptName;

    ComponentEnum[] componentEnums;

    ServiceEnum(String name, String scriptName, Class<? extends ComponentEnum> type){
        this.name = name;
        this.scriptName = scriptName;
        this.componentEnums = type.getEnumConstants();
    }

    /**
     * 组件枚举，
     * 使用interface将子枚举类型组织起来
     */
    public interface ComponentEnum{

        //jps  显示的名字
        String getName();
        // 执行命令是，创建的pid文件需要的名字，
        String getScriptName();

        CommandEnum[] getCommandEnums();

        enum YARN implements ComponentEnum{

            RESOURCEMANAGER("ResourceManager","ResourceManager",CommandEnum.ResourceManager.class),
            NODEMANAGER("NodeManager","NodeManager",CommandEnum.NodeManager.class);

            private String name;

            private String scriptName;
            CommandEnum[] commandEnums;

            YARN(String name,String scriptName,Class<? extends CommandEnum> command){
                this.name = name;
                this.scriptName = scriptName;
                this.commandEnums = command.getEnumConstants();
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getScriptName() {
                return scriptName;
            }

            @Override
            public CommandEnum[] getCommandEnums() {
                return commandEnums;
            }
        }

        enum HDFS implements ComponentEnum{

            //jps显示的名字，命令需要的名字
            NAMENODE("NameNode","namenode",CommandEnum.NameNode.class),
            DATANODE("DataNode","datanode",CommandEnum.DataNode.class),
            JOURNALNODE("JournalNode","journalnode",CommandEnum.JournalNode.class),
            ZKFC("DFSZKFailoverController","zkfc",CommandEnum.DFSZKFailoverController.class);

            private String name;

            private String scriptName;

            CommandEnum[] commandEnums;

            HDFS(String name,String scriptName,Class<? extends CommandEnum> command){
                this.name=name;
                this.scriptName=scriptName;
                this.commandEnums = command.getEnumConstants();
            }

            @Override
            public String getName(){
                return name;
            }
            @Override
            public String getScriptName(){
                return scriptName;
            }

            @Override
            public CommandEnum[] getCommandEnums() {
                return commandEnums;
            }
        }

        enum HBASE implements ComponentEnum{
            //jps显示的名字，命令需要的名字
            MASTER("HMaster","master",CommandEnum.HMaster.class),
            REGIONSERVER("HRegionServer","regionserver",CommandEnum.HRegionServer.class);

            private String name;

            private String scriptName;

            CommandEnum[] commandEnums;

            HBASE(String name,String scriptName,Class<? extends CommandEnum> command){
                this.name=name;
                this.scriptName=scriptName;
                this.commandEnums = command.getEnumConstants();
            }
            @Override
            public String getName(){
                return name;
            }
            @Override
            public String getScriptName(){
                return scriptName;
            }
            @Override
            public CommandEnum[] getCommandEnums() {
                return commandEnums;
            }
        }

    }

    /**
     * 命令，每个组件对应的命令
     */
    public interface CommandEnum{

        String getName();

        int getType();

        String getCommandShell();

        /* YARN  组件对应的命令 */
        enum ResourceManager implements CommandEnum{
            START("Start",1,""),
            STOP("Stop",1,"");

            private String name;

            private int type;

            private String commandShell;

            ResourceManager(String name,int type,String commandShell){
                this.name = name;
                this.commandShell = commandShell;
                this.type = type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getCommandShell() {
                return commandShell;
            }

            @Override
            public int getType() {
                return type;
            }
        }
        //YARN
        enum NodeManager implements CommandEnum{
            START("Start",1,""),
            STOP("Stop",1,"");

            private String name;

            private int type;

            private String commandShell;

            NodeManager(String name,int type,String commandShell){
                this.name = name;
                this.commandShell = commandShell;
                this.type = type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getCommandShell() {
                return commandShell;
            }

            @Override
            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        /* HDFS 组件对应的命令 */
        enum NameNode implements CommandEnum{

            START("Start",1,""),
            STOP("Stop",1,""),
            STARTALL("Start All",2,"");

            private String name;

            private int type;

            private String commandShell;

            NameNode(String name,int type,String commandShell){
                this.name = name;
                this.commandShell = commandShell;
                this.type = type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getCommandShell() {
                return commandShell;
            }

            @Override
            public int getType() {
                return type;
            }
        }

        enum DataNode implements CommandEnum{
            START("Start",1,""),
            STOP("Stop",1,"");

            private String name;

            private int type;

            private String commandShell;

            DataNode(String name,int type,String commandShell){
                this.name = name;
                this.commandShell = commandShell;
                this.type = type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getCommandShell() {
                return commandShell;
            }

            @Override
            public int getType() {
                return type;
            }
        }

        enum JournalNode implements CommandEnum{
            START("Start",1,""),
            STOP("Stop",1,"");

            private String name;

            private int type;

            private String commandShell;

            JournalNode(String name,int type,String commandShell){
                this.name = name;
                this.commandShell = commandShell;
                this.type = type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getCommandShell() {
                return commandShell;
            }

            @Override
            public int getType() {
                return type;
            }
        }

        enum DFSZKFailoverController implements CommandEnum{
            START("Start",1,""),
            STOP("Stop",1,"");

            private String name;

            private int type;

            private String commandShell;

            DFSZKFailoverController(String name,int type,String commandShell){
                this.name = name;
                this.commandShell = commandShell;
                this.type = type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getCommandShell() {
                return commandShell;
            }

            @Override
            public int getType() {
                return type;
            }
        }

        /* HBASE  组件对应的命令 */
        enum HMaster implements CommandEnum{
            START("Start",1,""),
            STOP("Stop",1,"");

            private String name;

            private int type;

            private String commandShell;

            HMaster(String name,int type,String commandShell){
                this.name = name;
                this.commandShell = commandShell;
                this.type = type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getCommandShell() {
                return commandShell;
            }

            @Override
            public int getType() {
                return type;
            }
        }

        enum HRegionServer implements CommandEnum{
            START("Start",1,""),
            STOP("Stop",1,"");

            private String name;

            private int type;

            private String commandShell;

            HRegionServer(String name,int type,String commandShell){
                this.name = name;
                this.commandShell = commandShell;
                this.type = type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getCommandShell() {
                return commandShell;
            }

            @Override
            public int getType() {
                return type;
            }
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public ComponentEnum[] getComponentEnums() {
        return componentEnums;
    }

    public void setComponentEnums(ComponentEnum[] componentEnums) {
        this.componentEnums = componentEnums;
    }

    public static ServiceEnum getEnum(String key) {
        for(ServiceEnum t : ServiceEnum.values()){
            if(t.getName().equals(key)){
                return t;
            }
        }
        throw new IllegalArgumentException("unknow type:" + key);
    }

    /**
     * 根据service的名字，获取所对应的组件component
     * @return
     */
    public static ComponentEnum[] getComponentEnumArr(String name){
        for(ServiceEnum t:ServiceEnum.values()){
            if(t.getName().equals(name)){
                return t.getComponentEnums();
            }
        }
        throw new IllegalArgumentException("unknow type:" + name);
    }

    /**
     * 将枚举类转换成list
     * @return
     */
    public static List getServiceEnumList(){
        List list = new ArrayList();
        for(ServiceEnum t:ServiceEnum.values()){
            Map map = new HashMap();
            map.put("name",t.name);
            map.put("scriptName",t.scriptName);

            List clist = new ArrayList();
            for(ComponentEnum c :t.getComponentEnums() ){
                Map cmap = new HashMap();
                cmap.put("name",c.getName());
                cmap.put("scriptName",c.getScriptName());

                List mlist = new ArrayList();
                for(CommandEnum cm:c.getCommandEnums()){
                    Map mmap = new HashMap();
                    mmap.put("name",cm.getName());
                    mmap.put("command",cm.getCommandShell());
                    mmap.put("type",cm.getType());
                    mlist.add(mmap);
                }

                cmap.put("commandEnums",mlist);
                clist.add(cmap);
            }

            map.put("componentEnums",clist);
            list.add(map);
        }
        return list;
    }

    public ComponentEnum getComponentEnum(String name){
        for(ComponentEnum c :this.getComponentEnums() ){
            if(c.getName().equals(name)){
                return c;
            }
        }

        throw new IllegalArgumentException("unknow type:" + name);
    }

    public static CommandEnum getCommandEnum(ComponentEnum component,String name){
        for(CommandEnum c :component.getCommandEnums() ){
            if(c.getName().equals(name)){
                return c;
            }
        }

        throw new IllegalArgumentException("unknow type:" + name);
    }

}
