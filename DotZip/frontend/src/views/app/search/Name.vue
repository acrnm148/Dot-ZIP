<template>
  <div>
    <b-row>
      <b-colxx xxs="12">
        <piaf-breadcrumb :heading="$t('menu.name')" />
        <div class="separator mb-5"></div>
      </b-colxx>
    </b-row>
    <b-row>
      <b-colxx xxs="12">
        <b-card class="mb-3">
          <name-search></name-search>
        </b-card>
        <b-card>
          <kakao-map> </kakao-map>
        </b-card>
      </b-colxx>
    </b-row>
  </div>
</template>

<script>
import KakaoMap from "@/components/Maps/KakaoMap.vue";
import NameSearch from "@/components/SearchView/NameSearch.vue";
export default {
  name: "Area",
  components: {
    KakaoMap,
    NameSearch
  }
};
</script>

<style></style>
