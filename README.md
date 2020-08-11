


### Swagger2  文档API使用说明

@Api：用在类上，说明该类的作用。

- tags="说明该类的作用，可以在UI界面上看到的注解"
- value="该参数没什么意义，在UI界面上也看到，所以不需要配置"

`
@Api(tags="APP用户注册Controller")
`

@ApiOperation：注解来给API增加方法说明。

- value="说明方法的用途、作用"
- notes="方法的备注说明"

`
@ApiOperation(value="用户注册",notes="手机号、密码都是必输项，年龄随边填，但必须是数字")
`

@ApiImplicitParams : 用在方法上包含一组参数说明。

- @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面

@ApiImplicitParam：注解用来给方法入参增加说明。

- name：参数名

- value：说明参数的意思

- required：参数是否必须传	true 或 false

- paramType：指定参数放在哪个地方	
  - header：请求参数放置于Request Header，使用@RequestHeader获取
  - query：请求参数放置于请求地址，使用@RequestParam获取
  - path：（用于restful接口）–>请求参数的获取：@PathVariable
  - body：（不常用）
  - form（不常用）

- dataType：参数类型	

- defaultValue：参数的默认值

`
@ApiImplicitParams({
    @ApiImplicitParam(name="mobile",value="手机号",required=true,paramType="form"),
    @ApiImplicitParam(name="password",value="密码",required=true,paramType="form"),
    @ApiImplicitParam(name="age",value="年龄",required=true,paramType="form",dataType="Integer")
})
`


@ApiResponses：用于表示一组响应

@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息

- code：数字，例如400
- message：信息，例如"请求参数没填好"
- response：抛出异常的类

`
@ApiOperation(value = "select1请求",notes = "多个参数，多种的查询参数类型")
@ApiResponses({
    @ApiResponse(code=400,message="请求参数没填好"),
    @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
})
`

@ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）

@ApiModelProperty:注解Model下的属性，当前端传过来的是一个对象时swagger中该对象的属性注解就是ApiModelProperty中的value

`
@ApiModel(description= "返回响应数据")
public class RestMessage implements Serializable{
 
    @ApiModelProperty(value = "是否成功")
    private boolean success=true;
    @ApiModelProperty(value = "返回对象")
    private Object data;
    @ApiModelProperty(value = "错误编号")
    private Integer errCode;
    @ApiModelProperty(value = "错误信息")
    private String message;
 
    /* getter/setter */
}
`

@ApiIgnore:注解类、参数、方法，注解后将不在Swagger UI中显示
	
@ApiParam:单个参数描述 (标注在参数上)；注解参数,hidden=true Swagger参数列表将不显示该参数,name对应参数名，value为注释，defaultValue设置默认值,allowableValues设置范围值,required设置参数是否必须，默认为false



**需要注意的是：**

ApiImplicitParam这个注解不只是注解，还会影响运行期的程序，例子如下：
　　
如果ApiImplicitParam中的phone的paramType是query的话，是无法注入到rest路径中的，而且如果是path的话，是不需要配置ApiImplicitParam的，即使配置了，其中的value="手机号"也不会在swagger-ui展示出来。


**访问 swagger-ui.html 即可显示出接口**

新增 swagger-bootstrap-ui 依赖， 优化了swagger原生的页面展示

依赖方式不变，访问路径为： 域名/项目名/doc.html   即可
















