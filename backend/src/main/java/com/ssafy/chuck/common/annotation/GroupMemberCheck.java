package com.ssafy.chuck.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GroupMemberCheck {
	/**
	 * 해당 유저가 그룹에 권한이 있는지 체크 필요시 해당 어노테이션
	 */
}
