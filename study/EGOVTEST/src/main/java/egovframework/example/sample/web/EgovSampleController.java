/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.ReplyVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@SessionAttributes(types = SampleVO.class)
public class EgovSampleController {
	
	Logger log = LoggerFactory.getLogger(EgovSampleController.class);

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "/sample/egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/sample/egovSampleList.do")
	public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> sampleList = sampleService.selectSampleList(searchVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = sampleService.selectSampleListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		log.debug("댓글을 제외한 목록의 갯수 : "+totCnt);

		return "/sample/egovSampleList";
	}

	/**
	 * 글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	@RequestMapping("/sample/selectSample.do")
	public @ModelAttribute("sampleVO")
	SampleVO selectSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		return sampleService.selectSample(sampleVO);
	}

	/**
	 * 글을 조회한다. (POST 방식만 처리한다)
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	@RequestMapping(value="/sample/egovSampleView.do",method={RequestMethod.POST})
	public String selectSampleView(@RequestParam("selectedId") String id,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setId(id);
		// 변수명은 CoC 에 따라 sampleVO
		sampleVO = selectSample(sampleVO, searchVO);
		model.addAttribute(sampleVO);
		
		if(!model.containsAttribute("replyVO")) {
			log.warn("ReplyVO를 생성(초기화) 합니다.");
			ReplyVO replyVO = new ReplyVO();
			model.addAttribute(replyVO);
		}
		
		List<SampleVO> replies = selectSampleReplyList(sampleVO, searchVO);
		log.debug(replies.toString());
		model.addAttribute("replies",replies);

		return "/sample/egovSampleView";
	}

	/**
	 * 글을 조회한다. (GET 방식만 처리한다)
	 * 댓글 입력시 폼 재전송(F5,Refresh를 통한 재입력)을 방지 하게 된다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	@RequestMapping(value="/sample/egovSampleView.do",method={RequestMethod.GET})
	public String selectSampleViewSafe(@RequestParam("selectedId") String id,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setId(id);
		// 변수명은 CoC 에 따라 sampleVO
		sampleVO = selectSample(sampleVO, searchVO);
		model.addAttribute(sampleVO);
		
		if(!model.containsAttribute("replyVO")) {
			log.warn("ReplyVO를 생성(초기화) 합니다.");
			ReplyVO replyVO = new ReplyVO();
			model.addAttribute(replyVO);
		}
		
		List<SampleVO> replies = selectSampleReplyList(sampleVO, searchVO);
		log.debug(replies.toString());
		model.addAttribute("replies",replies);

		return "/sample/egovSampleView";
	}

	/**
	 * 수정페이지로 이동한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	@RequestMapping("/sample/egovSampleModify.do")
	public String selectSampleModify(SampleVO sampleVO,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute(sampleVO);
		return "/sample/egovSampleRegister";
	}
	
	/**
	 * 댓글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public @ModelAttribute("sampleVO")
	List<SampleVO> selectSampleReplyList(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		return (List<SampleVO>) sampleService.selectSampleReplyList(sampleVO);
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/sample/addSampleView.do")
	public String addSampleView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "/sample/egovSampleRegister";
	}

	/**
	 * 글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/sample/addSample.do")
	public String addSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO,
			BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		// Server-Side Validation
		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "/sample/egovSampleRegister";
		}

		sampleService.insertSample(sampleVO);
		status.setComplete();
		return "forward:/sample/egovSampleList.do";
	}

	/**
	 * 댓글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/sample/replySample.do", method={RequestMethod.POST})
	public String replySample(
			@RequestParam("selectedId") String id,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, 
			@ModelAttribute("replyVO") @Valid ReplyVO replyVO,
			BindingResult bindingResult, Model model, WebRequest request, SessionStatus status)
			throws Exception {

		// Server-Side Validation
		beanValidator.validate(replyVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
		   log.warn("에러발생");
			model.addAttribute("replyVO", replyVO);
			return selectSampleView(id, searchVO, model);
		}
		sampleService.replySample(replyVO); 
		status.setComplete();
		// request.removeAttribute("replyVO", WebRequest.SCOPE_REQUEST); 
		// ---------------------------------------------------------------------------
		// for Reseting Previous Reply Data
		// ---------------------------------------------------------------------------
		Map<String,?> modelMap = model.asMap();
		modelMap.remove("replyVO");
		model.addAllAttributes(modelMap);
		// ---------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------
		// 폼(POST) 재전송 방지를 위해 Post-Redirect-Get Pattern in Spring MVC 패턴 적용
		// ----------------------------------------------------------------------------------
       return "redirect:/sample/egovSampleView.do?selectedId="+id;
	}

	/**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/sample/updateSampleView.do")
	public String updateSampleView(@RequestParam("selectedId") String id,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setId(id);
		// 변수명은 CoC 에 따라 sampleVO
		sampleVO = selectSample(sampleVO, searchVO);
		model.addAttribute(sampleVO);
		
		List<SampleVO> replies = selectSampleReplyList(sampleVO, searchVO);
		log.debug(replies.toString());
		model.addAttribute("replies",replies);

		return "/sample/egovSampleRegister";
	}

	/**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/sample/updateSample.do")
	public String updateSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO,
			BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "/sample/egovSampleRegister";
		}

		sampleService.updateSample(sampleVO);
		status.setComplete();
		return "forward:/sample/egovSampleList.do";
	}

	/**
	 * 글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/sample/deleteSample.do")
	public String deleteSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO,
			SessionStatus status) throws Exception {
		sampleService.deleteSample(sampleVO);
		status.setComplete();
		return "forward:/sample/egovSampleList.do";
	}

	/**
	 * 댓글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/sample/deleteReplySample.do")
	public String deleteReplySample(@RequestParam("selectedId") String id, SampleVO sampleVO,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model, SessionStatus status) throws Exception {

		log.debug("삭제할 댓글 ID : "+sampleVO.getId());
		log.debug("selectedID : "+id);

		sampleService.deleteReplySample(sampleVO);
		status.setComplete();
       return selectSampleView(id, searchVO, model);
	}

}
