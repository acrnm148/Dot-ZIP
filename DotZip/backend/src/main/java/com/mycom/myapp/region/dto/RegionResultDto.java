package com.mycom.myapp.region.dto;

import java.util.List;

public class RegionResultDto {
		private String result;
		private GugunCodeDto gugunDto;
		private DongCodeDto dongDto;
		private List<SidoCodeDto> sidoList;
		private List<GugunCodeDto> gugunList;
		private List<DongCodeDto> dongList;
		
		public String getResult() {
			return result;
		}


		public void setResult(String result) {
			this.result = result;
		}


		public GugunCodeDto getGugunDto() {
			return gugunDto;
		}


		public void setGugunDto(GugunCodeDto gugunDto) {
			this.gugunDto = gugunDto;
		}


		public DongCodeDto getDongDto() {
			return dongDto;
		}


		public void setDongDto(DongCodeDto dongDto) {
			this.dongDto = dongDto;
		}


		public List<SidoCodeDto> getSidoList() {
			return sidoList;
		}


		public void setSidoList(List<SidoCodeDto> sidoList) {
			this.sidoList = sidoList;
		}
		
		public List<GugunCodeDto> getGugunList() {
			return gugunList;
		}


		public void setGugunList(List<GugunCodeDto> gugunList) {
			this.gugunList = gugunList;
		}


		public List<DongCodeDto> getDongList() {
			return dongList;
		}


		public void setDongList(List<DongCodeDto> dongList) {
			this.dongList = dongList;
		}


		@Override
		public String toString() {
			return "RegionResultDto [result=" + result + ", gugunDto=" + gugunDto + ", dongDto=" + dongDto
					+ ", gugunList=" + gugunList + ", dongList=" + dongList + "]";
		}
		
		
}
