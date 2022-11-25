<template>
  <div>
    <div id="map">
      <favs-house-list-component></favs-house-list-component>
    </div>
  </div>
</template>

<script>
import FavsHouseListComponent from "@/components/FavsView/FavsHouseListComponent.vue";
import { mapGetters } from "vuex";
export default {
  name: "FavsMap",
  components: {
    FavsHouseListComponent
  },
  data() {
    return {
      markers: [],
      overlays: []
    };
  },
  computed: {
    ...mapGetters(["house", "bookmarkHouseList"])
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_SERVICE_KEY}`;
      document.head.appendChild(script);
    }
  },
  watch: {
    house() {
      this.map.setCenter(new kakao.maps.LatLng(this.house.lat, this.house.lng));
    }
  },
  methods: {
    removeMarker() {
      this.markers.forEach(marker => {
        marker.setMap(null);
      });
      this.markers = [];
    },
    removeOverlay() {
      this.overlays.forEach(overlay => {
        overlay.setMap(null);
      });
      this.overlays = [];
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.

      this.map = new kakao.maps.Map(container, options);
      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      // const mapTypeControl = new kakao.maps.MapTypeControl();

      // // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      // this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      // const zoomControl = new kakao.maps.ZoomControl();
      // this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
      if (this.bookmarkHouseList.length > 0) {
        this.makeMaker();
      }
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach(marker => marker.setMap(null));
      }
      const positions = markerPositions.map(
        position => new kakao.maps.LatLng(...position)
      );
      if (positions.length > 0) {
        this.markers = positions.map(
          position =>
            new kakao.maps.Marker({
              map: this.map,
              position
            })
        );
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );
        this.map.setBounds(bounds);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }
      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable
      });
      this.map.setCenter(iwPosition);
    },
    makeMaker() {
      let bounds = new kakao.maps.LatLngBounds();

      Array.from(this.bookmarkHouseList).forEach(house => {
        console.log("map" + house);
        const position = new kakao.maps.LatLng(house.lat, house.lng);
        const imageSrc =
          "https://img.icons8.com/plasticine/100/null/order-delivered.png"; // 마커이미지의 주소입니다
        const imageSize = new kakao.maps.Size(64, 69); // 마커이미지의 크기입니다
        const imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        const markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imageOption
        );
        const marker = new kakao.maps.Marker({ position, image: markerImage });

        this.markers.push(marker);
        marker.setMap(this.map);

        bounds.extend(position);
      });

      // 센터 위치 이동
      this.map.setBounds(bounds);
    },
    makeOverlay() {
      Array.from(this.bookmarkHouseList).forEach(house => {
        const position = new kakao.maps.LatLng(house.lat, house.lng);
        const content =
          '<div class="customoverlay">' +
          '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
          `    <span class="title">${house.aptName}</span>` +
          "  </a>" +
          "</div>";

        // 커스텀 오버레이를 생성합니다
        const overlay = new kakao.maps.CustomOverlay({
          map: this.map,
          position: position,
          content: content,
          yAnchor: 1
        });

        overlay.setMap(this.map);
        this.overlays.push(overlay);
      });
    }
  }
};
</script>
<style>
#map {
  width: 100%;
  height: 700px;
  position: relative;
  top: 0;
  left: 0;
}
.button-group {
  margin: 10px 0px;
}
button {
  margin: 0 3px;
}
.customoverlay {
  position: relative;
  bottom: 80px;
  left: 5px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #3188ff
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
