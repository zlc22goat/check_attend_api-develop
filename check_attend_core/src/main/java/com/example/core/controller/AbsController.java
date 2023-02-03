package com.example.core.controller;

import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.core.model.vo.Result;
import com.example.core.service.AbsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * ! 如非特别必要，请不要改动！！！！！！！
 *
 * 注意传入泛型
 * 一样的功能使用抽象类统一继承使用
 * ! 注意：这个类在大多数情况下不需要二次修改，即如果某个接口有附加的注意事项或者条件，那么就直接在对应的自己的Controller层override那个方法，保证这个抽象类足够的简洁有效，也方便后期维护
 *
 * ! TODO:如需要自定义权限的注解，如：@RequiresRoles()、@RequiresPermissions()，只需要override方法（如果没有特殊的业务重写必要，override之后不需要再写方法体，super.的调用是正确的），之后直接加上注解即可实现
 *
 * @author tzy
 * @since 2021/9/23 14:29
 */
public abstract class AbsController<M extends AbsService<T>, T> {

    @Autowired
    protected M baseService;

    protected Class<T> entityClass = this.currentModelClass();

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), 1);
    }

    // 获取对应的service
    public IService<T> getService(){
        return (IService<T>)this.baseService;
    };

    // 获取对应实体的类
    public Class<T> getEntityClass(){
        return this.entityClass;
    };

    // 新增
    @PostMapping("/create")
    @ApiOperation(value = "新增",notes = "新增信息")
    public Result<String> create(HttpServletRequest request, @RequestBody String requestBody) {
        return baseService.create(requestBody,getEntityClass(),getService());
    }


    // 批量删除 (通过confirmDelete判断是否可以直接删除)
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除",notes = "删除信息")
    public Result<String> delete(HttpServletRequest request,@RequestBody String requestBody) {
        return baseService.delete(requestBody,getService());
    }

    // 更新
    @PutMapping("/update")
    @ApiOperation(value = "更新",notes = "更新信息")
    public Result<String> update(HttpServletRequest request,@RequestBody String requestBody) {
        return baseService.update(requestBody,getEntityClass(),getService());
    }
    
    // 查询（不会深搜 即关联的数据也一并查询，需要根据业务场景重写）
    @PostMapping("/list")
    @ApiOperation(value = "列表",notes = "列出单个或多个对象的全部信息")
    public Result list(HttpServletRequest request,@RequestBody String requestBody) {
        return baseService.list(requestBody,getEntityClass(),getService());
    }

    // 下拉列表查询
    @GetMapping("/selectList")
    @ApiOperation(value = "下拉查询",notes = "获取这个对象对应的下拉框(并不是所有对象都支持)")
    public Result selectList() {
        return baseService.selectList(getEntityClass(),getService());
    }

    // 细节查询(根据id) - restful风格
    @GetMapping("/{id}/detail")
    @ApiOperation(value = "细节查询",notes = "查询某个对象的全部信息(关联的也一并被查询)")
    public Result detail(@PathVariable("id") String id){
        return baseService.detail(id,getService(),getEntityClass());
    }
}

