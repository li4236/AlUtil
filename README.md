
- 2018.1.2 添加两种mvp测试示例

++++++++++++++++++++++++


>初始化
     
     List<Interceptor> interceptors = new ArrayList<>();
    //比如日志拦截输
    interceptors.add(new LogInterceptor());
    //        网络统一头部添加处理
    interceptors.add(new HeadInterceptor());
    
    ***
    AlConfig.init()
    //初始化基础api
    .baseUrl("http://v.juhe.cn/toutiao/")
    //初始化下载文件保存路径
    .downlaodFath(sdcardPath+"/AlDownload")
    .interceptors(interceptors);
  
   
> url说明

    AlHttp.Get(url,null,null,null)
    url:可以填写全链接或者是拼接基础api

    比如：

    //全链接
   
    AlHttp.Get("http://dldir1.qq.com/weixin/",null,null,null)
    
    //拼接类型
    
    AlHttp.Get("index?",null,null,null)
    
   
   
   
   