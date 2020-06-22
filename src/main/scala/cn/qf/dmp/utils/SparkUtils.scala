package cn.qf.dmp.utils

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object SparkUtils {

    /**
     * 获取到SparkContext的入口
     */
    def getSparkContext(appName:String, master:String) : SparkContext = {
        new SparkContext(new SparkConf().setAppName(appName).setMaster(master))
    }

    def getLocalSparkContext(appName:String): SparkContext = {
        getSparkContext(appName, "local[*]")
    }

    /**
     * 获取SparkSql的入口
     */
    def getSparkSession(appName:String, master:String) : SparkSession = {
        SparkSession.builder().appName(appName).master(master).getOrCreate()
    }

    /**
     * 支持hive的sparkSession
     */
    def getSparkSessionSupportHive(appName:String, master:String) : SparkSession = {
        SparkSession.builder().appName(appName).master(master).enableHiveSupport().getOrCreate()
    }

    def getLocalSparkSession(appName:String): SparkSession = {
        getSparkSession(appName, "local[*]")
    }

    def getLocalSparkSession(appName:String, supportHive:Boolean): SparkSession = {
        if (supportHive) getSparkSessionSupportHive(appName, "local[*]")
        else getSparkSession(appName, "local[*]")
    }


    /**
     * 释放资源
     */
    def stop(sc:SparkContext) : Unit = {
        if (sc != null) sc.stop()
    }
    def stop(ss:SparkSession) : Unit = {
        if (ss != null) ss.stop()
    }
    def stop(sc:SparkContext, ss:SparkSession) : Unit = {
        if (sc != null) sc.stop()
        if (ss != null) ss.stop()
    }
}
