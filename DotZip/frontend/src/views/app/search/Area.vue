<template>
  <div>
    <b-row>
      <b-colxx xxs="12">
        <piaf-breadcrumb :heading="$t('menu.area')" />
        <div class="separator mb-5"></div>
      </b-colxx>
    </b-row>
    <b-row>
      <b-colxx xxs="12">
        <dong-search></dong-search>
        <b-card>
          <kakao-map> </kakao-map>
        </b-card>
      </b-colxx>
    </b-row>
  </div>
</template>

<script>
import KakaoMap from "@/components/Maps/KakaoMap.vue";
import DongSearch from "@/components/SearchView/DongSearch.vue";
export default {
  name: "Area",
  components: {
    KakaoMap,
    DongSearch,
  },
};
</script>

<style></style>
