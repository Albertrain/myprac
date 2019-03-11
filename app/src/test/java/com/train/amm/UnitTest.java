package com.train.amm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * #测试
 * * 黑盒测试
 * 	* 测试逻辑业务
 * * 白盒测试
 * 	* 测试逻辑方法
 *
 * * 根据测试粒度
 * 	* 方法测试：function test
 * 	* 单元测试：unit test
 * 	* 集成测试：integration test
 * 	* 系统测试：system test
 *
 * * 根据测试暴力程度
 * 	* 冒烟测试：smoke test
 * 	* 压力测试：pressure test
 *
 * ------
 * #单元测试junit
 * * 定义一个类继承AndroidTestCase，在类中定义方法，即可测试该方法
 *
 *
 * * 在指定指令集时，targetPackage指定你要测试的应用的包名
 *
 * 		<instrumentation
 * 	    android:name="android.test.InstrumentationTestRunner"
 * 	    android:targetPackage="com.itheima.junit"
 * 	    ></instrumentation>
 *
 * * 定义使用的类库
 *
 * 		<uses-library android:name="android.test.runner"></uses-library>
 *
 * * 断言的作用，检测运行结果和预期是否一致
 * * 如果应用出现异常，会抛给测试框架
 *
 * 以上都是在eclipse环境中的
 */
public class UnitTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void isEmpty() {

    }

    @Test
    public void test(){


    }

}
