### 呆萝比 biz 模板应用使用说明

+ 默认自动接入 dubbo RPC远程调用功能, 提供 dubbo 服务注册和消费能力
+ 默认自动接入 actuator 监控功能, 提供监控应用性能和运行状态的能力
+ 集成其他功能,如: zeye 调用链追踪, zk, 公共工具等

#### dubbo 使用
+ 配置 dubbo 服务提供者
> 在 src/main/resources 目录下创建 spring/dubbo-provider.xml 文件
> 并填写如下内容, interface 和 class 填写实际需要提供的 dubbo 服务名和实现类.

``` xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.springframework.org/schema/beans            http://www.springframework.org/schema/beans/spring-beans.xsd            http://code.alibabatech.com/schema/dubbo            http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <dubbo:service interface="cn.zcy.gov.TestDubboService" class="cn.zcy.gov.TestDubboServiceImpl" version="1.0.0"/>
</beans>
```

+ 配置 dubbo 服务消费者
> 在 src/main/resources 目录下创建 spring/dubbo-provider.xml文件
> 并填写如下内容, interface 和 id 填写实际需要消费的 dubbo 服务名.

``` xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.springframework.org/schema/beans            http://www.springframework.org/schema/beans/spring-beans.xsd            http://code.alibabatech.com/schema/dubbo            http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <dubbo:reference id="documentCenterPlugService" interface="cn.gov.zcy.dc.plug.service.DocumentCenterPlugService" version="1.0.0"/>
</beans>
```

#### 其他组件使用
+ 每个组件使用都应该有相应接入使用文档,且能够实现,自助查阅文档,即可自助接入.
+ 本 archetype 应用内所集成的组件, 在父pom dependencyManagement 中均附有接入文档, 若有不明白之处, 可先通过查阅接入文档自行调试.
+ 如发现本 archetype 所依赖 jar 包版本滞后, 请联系骨架管理员进行调整更新.




#### 支持sofa-rpc功能
1. 引入配置文件
```$xslt
com:
  alipay:
    sofa:
      rpc:
        registry:
          address: zookeeper://127.0.0.1:2181/sofa-rpc # 注册中心 & server端和client端都需要维护
        boltPort: 12200 # server 端需要制定
```
2. server端代码，参考实现类 `com.mallcai.biz.service.api.SofaRpcApiImpl`
```$xslt
@RequestMapping("/banner")
@RestController
@SofaService(bindings = {@SofaServiceBinding(bindingType = "bolt", timeout = 20000)})
public class SofaRpcApiImpl implements SofaRpcApi {

    // 方法
    @RequestMapping(value = "bannerlist", method = RequestMethod.GET)
    public RespEntity getBannerList(String local) {
        return bannerService.getBannerList(local);
    }
}
```
+ client端代码，参考如下
```$xslt

    // 引入后即可使用
    @SofaReference(binding = @SofaReferenceBinding(bindingType = "bolt", timeout = 20000))
    private SofaRpcApi sofaRpcApi;
```

