package com.thinkgem.jeesite.test;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;

import com.thinkgem.jeesite.modules.sys.service.TestService;
import com.thinkgem.jeesite.tools.SpringTransactionalContextTests;

public class TestLdap extends SpringTransactionalContextTests{

	@Autowired
	private  TestService testService;
	
	@Test
	public void  testss() {
		
		// TODO Auto-generated method stub

//		ApplicationContext ac=new ClassPathXmlApplicationContext("spring-context-ldap.xml");
//		TestService testService=(TestService)ac.getBean("testService");
		LdapQuery query = LdapQueryBuilder.query()
				.where("objectCategory").is("person")
				.and("objectClass").is("user")
//				.and("objectClass").is("group")
//				.and("description").is("徐继盛")
//				.and("userPrincipalName").is("jishengxu@quark.com")
				;
		List<Person> persons = testService.getLdapTemplate().search(query, new PersonContextMapper<>());
		System.out.println(persons.size());
		
		LdapQuery query1 = LdapQueryBuilder.query()
				.where("objectClass").is("group")
				;
		List<Group> groups = testService.getLdapTemplate().search(query1, new GroupContextMapper<>());
		System.out.println(groups.size());
	}
	
	

	

	private static class PersonContextMapper<T> implements ContextMapper<Person> {
		public Person mapFromContext(Object ctx) {
			DirContextAdapter context = (DirContextAdapter) ctx;
			Person p = new Person();
			p.setName(context.getStringAttribute("name"));
			p.setGivename(context.getStringAttribute("giveName"));
			p.setUserPrincipalName(context.getStringAttribute("userPrincipalName"));
			p.setMail(context.getStringAttribute("mail"));
			p.setMailNickName(context.getStringAttribute("mailNickName"));
			p.setMemberof(context.getStringAttribute("memberof"));
			p.setDescription(context.getStringAttribute("description"));
			p.setTitle(context.getStringAttribute("title"));
			p.setDepartment(context.getStringAttribute("department"));
			p.setLastlogon(context.getStringAttribute("lastlogon"));
			p.setLastlogontimestamp(context.getStringAttribute("lastlogontimestamp"));
			p.setManager(context.getStringAttribute("manager"));
			p.setCn(context.getStringAttribute("cn"));
			p.setDisplayName(context.getStringAttribute("displayName"));
			p.setDistinguishedName(context.getStringAttribute("distinguishedName"));
			p.setPwdLastSet(context.getStringAttribute("pwdLastSet"));
			p.setBadpasswordtime(context.getStringAttribute("badpasswordtime"));
			p.setTelephoneNumber(context.getStringAttribute("telephoneNumber"));
			return p;
		}
	}
	
	private static class GroupContextMapper<T> implements ContextMapper<Group> {
		public Group mapFromContext(Object ctx) {
			DirContextAdapter context = (DirContextAdapter) ctx;
			Group g = new Group();
			g.setName(context.getStringAttribute("name"));
			g.setMail(context.getStringAttribute("mail"));
			g.setMailNickName(context.getStringAttribute("mailNickName"));
			g.setMember(context.getStringAttribute("member"));
			g.setManagedBy(context.getStringAttribute("managedBy"));
			g.setCn(context.getStringAttribute("cn"));
			g.setDisplayName(context.getStringAttribute("displayName"));
			g.setDistinguishedName(context.getStringAttribute("distinguishedName"));
			return g;
		}
	}

	/*
	 * 查用户
	 */
	
	/*
	 * 查组织
	 */

	/**
	 * @ClassName: Group
	 * @author:  [jishengxu] 
	 * @CreateDate: [2017年3月27日 下午3:14:10]   
	 * @UpdateUser: [jishengxu]   
	 * @UpdateDate: [2017年3月27日 下午3:14:10]   
	 * @UpdateRemark: [说明本次修改内容]
	 * @Description:  [域账号，组织结构]
	 * @version: [V1.0]
	 */
	private static class Group {
		// 名字
		private String name;
		// 邮箱
		private String mail;
		// 邮箱去除@后缀
		private String mailNickName;
		// 组织成员
		private String member;
		// 管理员dn
		private String managedBy;
		// 组织名字
		private String cn;
		// 显示的全名
		private String displayName;
		// 自己的dn
		private String distinguishedName;
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the mail
		 */
		public String getMail() {
			return mail;
		}
		/**
		 * @param mail the mail to set
		 */
		public void setMail(String mail) {
			this.mail = mail;
		}
		/**
		 * @return the mailNickName
		 */
		public String getMailNickName() {
			return mailNickName;
		}
		/**
		 * @param mailNickName the mailNickName to set
		 */
		public void setMailNickName(String mailNickName) {
			this.mailNickName = mailNickName;
		}
		/**
		 * @return the member
		 */
		public String getMember() {
			return member;
		}
		/**
		 * @param member the member to set
		 */
		public void setMember(String member) {
			this.member = member;
		}
		/**
		 * @return the managedBy
		 */
		public String getManagedBy() {
			return managedBy;
		}
		/**
		 * @param managedBy the managedBy to set
		 */
		public void setManagedBy(String managedBy) {
			this.managedBy = managedBy;
		}
		/**
		 * @return the cn
		 */
		public String getCn() {
			return cn;
		}
		/**
		 * @param cn the cn to set
		 */
		public void setCn(String cn) {
			this.cn = cn;
		}
		/**
		 * @return the displayName
		 */
		public String getDisplayName() {
			return displayName;
		}
		/**
		 * @param displayName the displayName to set
		 */
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		/**
		 * @return the distinguishedName
		 */
		public String getDistinguishedName() {
			return distinguishedName;
		}
		/**
		 * @param distinguishedName the distinguishedName to set
		 */
		public void setDistinguishedName(String distinguishedName) {
			this.distinguishedName = distinguishedName;
		}
		
	}
	
	/**
	 * @ClassName: Person
	 * @author:  [jishengxu] 
	 * @CreateDate: [2017年3月27日 下午3:13:37]   
	 * @UpdateUser: [jishengxu]   
	 * @UpdateDate: [2017年3月27日 下午3:13:37]   
	 * @UpdateRemark: [说明本次修改内容]
	 * @Description:  [域账号，人]
	 * @version: [V1.0]
	 */
	private static class Person {
		// 名字
		private String name;
		// 姓,Object，不能直接用String获取
		private String givename;
		// 用户主体名，邮箱值
		private String userPrincipalName;
		// 邮箱
		private String mail;
		// 邮箱去除@后缀
		private String mailNickName;
		// 归属
		private String memberof;
		// 描述（中文名）
		private String description;
		// 职位描述
		private String title;
		// 部门
		private String department;
		// 最后登录时间
		private String lastlogon;
		private String lastlogontimestamp;
		// 上级领导
		private String manager;
		// 姓名（中间有空格）
		private String cn;
		// 显示的全名（中英文）
		private String displayName;
		// 自己的dn
		private String distinguishedName;
		// 最后一次修改密码时间
		private String pwdLastSet;
		// 密码过期时间
		private String badpasswordtime;
		// 电话
		private String telephoneNumber;
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the givename
		 */
		public String getGivename() {
			return givename;
		}
		/**
		 * @param givename the givename to set
		 */
		public void setGivename(String givename) {
			this.givename = givename;
		}
		/**
		 * @return the mail
		 */
		public String getMail() {
			return mail;
		}
		/**
		 * @param mail the mail to set
		 */
		public void setMail(String mail) {
			this.mail = mail;
		}
		/**
		 * @return the mailNickNmae
		 */
		public String getMailNickName() {
			return mailNickName;
		}
		/**
		 * @param mailNickNmae the mailNickNmae to set
		 */
		public void setMailNickName(String mailNickName) {
			this.mailNickName = mailNickName;
		}
		/**
		 * @return the memberof
		 */
		public String getMemberof() {
			return memberof;
		}
		/**
		 * @param memberof the memberof to set
		 */
		public void setMemberof(String memberof) {
			this.memberof = memberof;
		}
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * @param title the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		/**
		 * @return the department
		 */
		public String getDepartment() {
			return department;
		}
		/**
		 * @param department the department to set
		 */
		public void setDepartment(String department) {
			this.department = department;
		}
		/**
		 * @return the lastlogon
		 */
		public String getLastlogon() {
			return lastlogon;
		}
		/**
		 * @param lastlogon the lastlogon to set
		 */
		public void setLastlogon(String lastlogon) {
			this.lastlogon = lastlogon;
		}
		/**
		 * @return the lastlogontimestamp
		 */
		public String getLastlogontimestamp() {
			return lastlogontimestamp;
		}
		/**
		 * @param lastlogontimestamp the lastlogontimestamp to set
		 */
		public void setLastlogontimestamp(String lastlogontimestamp) {
			this.lastlogontimestamp = lastlogontimestamp;
		}
		/**
		 * @return the manager
		 */
		public String getManager() {
			return manager;
		}
		/**
		 * @param manager the manager to set
		 */
		public void setManager(String manager) {
			this.manager = manager;
		}
		/**
		 * @return the cn
		 */
		public String getCn() {
			return cn;
		}
		/**
		 * @param cn the cn to set
		 */
		public void setCn(String cn) {
			this.cn = cn;
		}
		/**
		 * @return the displayName
		 */
		public String getDisplayName() {
			return displayName;
		}
		/**
		 * @param displayName the displayName to set
		 */
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		/**
		 * @return the distinguishedName
		 */
		public String getDistinguishedName() {
			return distinguishedName;
		}
		/**
		 * @param distinguishedName the distinguishedName to set
		 */
		public void setDistinguishedName(String distinguishedName) {
			this.distinguishedName = distinguishedName;
		}
		/**
		 * @return the pwdLastSet
		 */
		public String getPwdLastSet() {
			return pwdLastSet;
		}
		/**
		 * @param pwdLastSet the pwdLastSet to set
		 */
		public void setPwdLastSet(String pwdLastSet) {
			this.pwdLastSet = pwdLastSet;
		}
		/**
		 * @return the badpasswordtime
		 */
		public String getBadpasswordtime() {
			return badpasswordtime;
		}
		/**
		 * @param badpasswordtime the badpasswordtime to set
		 */
		public void setBadpasswordtime(String badpasswordtime) {
			this.badpasswordtime = badpasswordtime;
		}
		/**
		 * @return the telephoneNumber
		 */
		public String getTelephoneNumber() {
			return telephoneNumber;
		}
		/**
		 * @param telephoneNumber the telephoneNumber to set
		 */
		public void setTelephoneNumber(String telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
		}
		/**
		 * @return the userPrincipalName
		 */
		public String getUserPrincipalName() {
			return userPrincipalName;
		}
		/**
		 * @param userPrincipalName the userPrincipalName to set
		 */
		public void setUserPrincipalName(String userPrincipalName) {
			this.userPrincipalName = userPrincipalName;
		}
		
	}
}
