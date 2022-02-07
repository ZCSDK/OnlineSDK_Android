package com.sobot.online.adapter;

/**
 * <p>描述：写一个用于模拟测试的bean</p>

 * 日期： 2016/10/27 16:25<br>

 */
public class TestBean {
    private String name;
    private String age;

    public TestBean(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
