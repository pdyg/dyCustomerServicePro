package com.dyCustomerService.util.memcached;

import java.io.IOException;
import java.util.Date;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.CASResponse;
import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

public class Memcahched {
	static Logger logger = Logger.getLogger(Memcahched.class.getName());
	private static MemcachedClient mcc = null;
	// private static final String host =
	// "m-bp16a413a8ae53e4.memcache.rds.aliyuncs.com";// 控制台上的“内网地址”
	private static final String host = "127.0.0.1";// 控制台上的“内网地址”
	private static final String port = "11211"; // 默认端口 11211，不用改
	private static Date time = new Date(0);
	private static final Object lockobj = new Object();

	/*
	 * WechatFansServicesI wechatFansServicesI = (WechatFansServicesI) helper
	 * .getContext().getBean("WechatFansServicesImpl");
	 */

	static {
		try {

			mcc = new MemcachedClient(new BinaryConnectionFactory(),
					AddrUtil.getAddresses(host + ":" + port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected Memcahched() {
	}

	protected static Memcahched instance = new Memcahched();

	/**
	 * 为受保护的对象提供一个公共的访问方法
	 */
	public static Memcahched getInstance() {

		if (mcc == null) {
			synchronized (lockobj) {
				if (mcc == null) {
					try {
						mcc = new MemcachedClient(
								new BinaryConnectionFactory(),
								AddrUtil.getAddresses(host + ":" + port));
						logger.info("初始Memcached化缓存成功");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}

			}

		}

		return instance;
	}

	/**
	 * 插入一条记录到缓存服务器中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) {

		if (key == null) {
			return false;
		} else {
			try {
				return mcc.add(key, 0, value) != null;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.add(key, 0, value) != null;
			}

		}
	}

	/**
	 * 插入一条记录到缓存服务器，并设置过期时间，单位为秒
	 * 
	 * @param key
	 * @param value
	 * @param expiry
	 * @return
	 */
	public boolean add(String key, Object value, Date expiry) {

		if (key == null) {
			return false;
		} else {
			long ll = expiry.getTime() - time.getTime();
			int ii = new Long(ll).intValue();
			try {
				return mcc.add(key, ii / 1000, value) != null;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.add(key, ii / 1000, value) != null;
			}

		}
	}

	public boolean add(String key, Object value, int expiry) {
		if (key == null) {
			return false;
		} else {

			try {
				return mcc.add(key, expiry, value) != null;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.add(key, expiry, value) != null;
			}
		}

	}

	/**
	 * 根据KEY替换缓存服务器中的数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean replace(String key, Object value) {
		if (key == null) {
			return false;
		} else {

			try {
				return mcc.replace(key, 0, value) != null;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.replace(key, 0, value) != null;
			}
		}

	}

	public boolean replace(String key, int value, Date expiry) {

		if (key == null) {
			return false;
		} else {
			long ll = expiry.getTime() - time.getTime();
			int ii = new Long(ll).intValue();

			try {
				return mcc.replace(key, value, ii) != null;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.replace(key, value, ii) != null;
			}
		}
	}

	/**
	 * 当KEY不存在时将数据添加到缓存服务器中 当KEY存在时则替换原来的数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {

		if (key == null) {
			return false;
		} else {
			try {
				return mcc.set(key, 0, value) != null;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.set(key, 0, value) != null;
			}

		}
	}

	public boolean set(String key, Object value, Date expiry) {
		if (key == null) {
			return false;
		} else {
			long ll = expiry.getTime() - time.getTime();
			int ii = new Long(ll).intValue();
			try {
				return mcc.set(key, ii / 1000, value) != null;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.set(key, ii / 1000, value) != null;
			}

		}

	}

	public boolean set(String key, Object value, int expiry) {
		if (key == null) {
			return false;
		} else {
			try {
				return mcc.set(key, expiry, value) != null;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.set(key, expiry, value) != null;
			}

		}

	}

	/**
	 * 根据KEY删除缓存服务器中的数据
	 * 
	 * @param key
	 * @return
	 */
	public boolean delete(String key) {
		if (key == null) {
			return false;
		} else {
			try {
				return mcc.delete(key) != null;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.delete(key) != null;
			}

		}

	}

	/**
	 * 根据指定的KEY获取数据
	 */
	public Object get(String key) {
		if (key == null) {
			return null;
		} else {
			try {
				return mcc.get(key);
			} catch (Exception e) {
				// TODO: handle exception

				try {
					mcc = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
					logger.info("初始Memcached化缓存成功");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				return mcc.get(key);
			}

		}

	}

	/**
	 * 判断指定的KEY是否已经存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean keyExists(String key) {

		if (key == null) {
			return false;
		} else {
			return mcc.get(key) != null;
		}
	}

	public boolean cas(String key) {
		if (key == null) {
			return false;
		} else {
			CASResponse gr = mcc.cas(key, 1000, new Date());
			if (gr.toString().equals("EXISTS")) {
				return true;
			} else {
				return false;
			}

		}

	}
}
