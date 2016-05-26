package com.dreamy.service.iface;

/**
 * 
 * @author jared
 * 
 * @Description:
 * 
 * @date Dec 11, 2014 9:58:01 AM
 * 
 */
public interface CommonService {

	/**
	 * 获取当前工作环境是否是开发环境
	 * 
	 * @return
	 */
	boolean isDev();

	/**
	 * 获取项目主站域名
	 * 
	 * @return
	 */
	String getDomain();


    /**
     *
     * @return
     */
    String getAssetsDomain();
}
