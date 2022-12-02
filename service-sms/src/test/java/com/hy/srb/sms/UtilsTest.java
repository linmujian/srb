package com.hy.srb.sms;

import com.hy.srb.sms.utils.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class UtilsTest {

    @Test
    public void testProperties(){
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(1));
        list.add(String.valueOf(2));
        list.add(String.valueOf(3));
//        String s = objects.toString();
        String.valueOf(list);
        System.out.println(list);

    }

//    public int[] twoSum(int[] nums, int target) {
//        int[] result=new int[]{};
//        for(int i=0;nums.;i++){
//            for(int j=i+1;nums[j]!=null;j++){
//                if(nums[i]+nums[j]==target){
//                    result[0]=i;
//                    result[1]=j;
//                    return result;
//                }
//            }
//        }
//
//        return null;
//    }

}
