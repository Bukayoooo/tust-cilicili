package com.tust.bilibili.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试Restfull类型接口
 */
@RestController
public class RestfulController{

    private final Map<Integer, Map<String, Object>> dataMap;

    public RestfulController(){
        dataMap = new HashMap<>();
        //  填充dataMap
        for(int i = 0; i < 3; i++){
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("data", "name" + i);
            dataMap.put(i, data);
        }
    }

    /**
     * @return dataMap中的数据
     */
    @GetMapping("/objects/{id}")
    public Map<String, Object> getData(@PathVariable int id){
        return dataMap.get(id);
    }

    /**
     * 删除dataMap中的数据
     */
    @DeleteMapping("/objects/{id}")
    public String delData(@PathVariable int id){
        dataMap.remove(id);
        return "delete success";
    }

    /**
     * 往dataMap中添加新的数据
     * post每次都是往事件组里插入新的数据，多次操作带来的影响不同，所以不具备幂等性
     * post适用于密码，博文等表单数据的提交工作
     */
    @PostMapping("/objects")
    public String postData(@RequestBody Map<String, Object> data){
        // 1. 获取添加位置的id
        Integer[] idArrays = dataMap.keySet().toArray(new Integer[0]);
        Arrays.sort(idArrays);
        int nextId = idArrays[idArrays.length - 1] + 1;
        // 2. 插入dataMap中
        dataMap.put(nextId, data);
        return "post success";
    }

    /**
     * 往dataMap中插入或更新操作
     * 没有数据时直接插入，有数据时则更新；因为put请求仅对要修改的资源有影响，所以具备幂等性
     * put适用于用户名，昵称信息的更新工作
     */
    @PutMapping("/objects")
    public String putData(@RequestBody Map<String, Object> data){
        // 1. 根据data的id查找dataMap中是否有改数据
        Integer id = Integer.parseInt(String.valueOf(data.get("id")));
        Map<String, Object> dataRst = dataMap.get(id);
        if(dataRst == null) {
            // 2. 没有该数据，执行和上面post一样的插入数据
            // 2.1. 获取添加位置的id
            Integer[] idArrays = dataMap.keySet().toArray(new Integer[0]);
            Arrays.sort(idArrays);
            int nextId = idArrays[idArrays.length - 1] + 1;
            // 2.2. 插入dataMap中
            dataMap.put(nextId, data);
        }else {
            // 3. 有该数据，更新数据，直接将原来的数据覆盖掉
            dataMap.put(id, data);
        }
        return "put success";
    }
}
