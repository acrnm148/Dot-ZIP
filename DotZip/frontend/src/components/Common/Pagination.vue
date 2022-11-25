<template
  ><b-pagination-nav
    class="pagination_wrapper"
    :number-of-pages="5"
    :link-gen="linkGen"
    v-model="currentPage"
    :per-page="5"
  >
    <template v-slot:next-text>
      <i class="simple-icon-arrow-right" />
    </template>
    <template v-slot:prev-text>
      <i class="simple-icon-arrow-left" />
    </template>
    <template v-slot:first-text>
      <i class="simple-icon-control-start" />
    </template>
    <template v-slot:last-text>
      <i class="simple-icon-control-end" />
    </template>
  </b-pagination-nav>
</template>

<script>
export default {
  data() {
    return {
      currentPage: 1,
      breadcrumbItems: [
        {
          text: "Home",
          href: "#",
        },
        {
          text: "Library",
          href: "#",
        },
        {
          text: "Data",
          href: "#",
        },
      ],
    };
  },
  methods: {
    linkGen(pageNum) {
      return "#page/" + pageNum + "/foobar";
    },
  },
};
</script>

<style>
.pagination_wrapper {
  display: flex;
  justify-content: center;
}
</style>
