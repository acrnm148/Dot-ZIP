<template>
  <div>
    <b-row>
      <b-colxx xxs="12">
        <piaf-breadcrumb :heading="$t('menu.fHouse')" />
        <div class="separator mb-5"></div>
      </b-colxx>
    </b-row>
    <b-row>
      <b-colxx xxs="12">
        <b-card>
          <favs-map> </favs-map>
        </b-card>
      </b-colxx>
    </b-row>
  </div>
</template>
<script>
import FavsMap from "@/components/Maps/FavsMap.vue";
import {mapGetters} from "vuex";
export default {
  name: "FavHouse",
  components: {
    FavsMap,
  },
  computed: {
    ...mapGetters(["currentUser"]),
  },
};
</script>

<style></style>
