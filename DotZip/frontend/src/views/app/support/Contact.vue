<template>
  <div>
    <b-row>
      <b-colxx xxs="12">
        <piaf-breadcrumb :heading="$t('menu.contact')" />
        <div class="separator mb-5"></div>
      </b-colxx>
    </b-row>
    <b-row>
      <b-colxx xxs="12">
        <b-card class="mb-4" :title="$t('menu.contact')">
          <table>
            <tr>
              <td>
                <br />
                <iframe
                  width="560"
                  height="315"
                  src="https://www.youtube.com/embed/an_0IDaPLw8"
                  title="YouTube video player"
                  frameborder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                  allowfullscreen
                ></iframe>
              </td>
              <td>
                <!-- <h3 id="wrapperheader">오늘의 뉴스</h3> -->
                <div class="scrollBar">
                  <div
                    id="news"
                    v-for="(item, idx) in items"
                    :key="idx"
                    style="cursor: pointer"
                    @click="item.url"
                  >
                    <img src="item.urlToImg" alt="" />
                    <div id="author">{{ item.author }}</div>
                    <div id="title">{{ item.title }}</div>
                    <!-- <div id="urlToImg">{{ item.urlToImg }}</div> -->
                    <div id="publishedAt">
                      {{ item.publishedAt }}
                      <!-- {{ subString(item.publishedAt, 0, 10) }} -->
                    </div>
                    <div id="url" @click="goNews(item.url)">자세히보기 ></div>
                  </div>
                </div>
              </td>
            </tr>
          </table>
        </b-card>
      </b-colxx>
    </b-row>
  </div>
</template>

<script>
import http from "@/apis/axios.js"; //axios객체
// import axios from "axios";

export default {
  data() {
    return {
      items: []
    };
  },
  method: {
    goNews(url) {
      window.open(url, "_blank");
    },
    pubtime(publishedAt) {
      // subString(publishedAt);
      return publishedAt;
    }
  },
  created() {
    let $this = this;
    http
      .get(
        "https://newsapi.org/v2/top-headlines?country=kr&apiKey=e52a60ef17894643aa0f85739a3f96fc"
      )
      .then(function(response) {
        const {
          data: { articles }
        } = response;
        $this.items = [...articles];
      })
      .catch(function(error) {
        console.log(error);
      });
  },
  watch: {
    items() {
      console.log(this.items);
    }
  }
};
</script>
<style scoped>
table td {
  vertical-align: top;
  padding: 40px;
}
#author {
  color: rgb(95, 95, 95);
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 2px;
}
#title {
  color: black;
  font-size: 22px;
  font-weight: bold;
  margin-bottom: 5px;
}

#url {
  color: gray;
  margin-top: 10px;
  margin-bottom: 20px;
}
#news {
  padding: 15px;
  padding-left: 30px;
  border: 1px solid lightgray;
  border-radius: 10px;
  margin-bottom: 15px;
}
.scrollBar {
  padding: 15px;
  height: 600px;
  overflow-y: scroll;
}
#wrapperheader {
  color: black;
  font-weight: bold;
  font-size: 40px;
}

/* 아래의 모든 코드는 영역::코드로 사용 */
.scrollBar::-webkit-scrollbar {
  width: 10px; /* 스크롤바의 너비 */
}

.scrollBar::-webkit-scrollbar-thumb {
  height: 30%; /* 스크롤바의 길이 */
  background: #217af4; /* 스크롤바의 색상 */
  border-radius: 10px;
}

.scrollBar::-webkit-scrollbar-track {
  background: rgba(33, 122, 244, 0.1); /*스크롤바 뒷 배경 색상*/
}
#publishedAt {
  color: rgb(148, 148, 148);
  font-size: 14px;
}
</style>
