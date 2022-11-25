<template>
  <b-row class="h-100">
    <b-colxx xxs="12" md="10" class="mx-auto my-auto">
      <b-card class="auth-card" no-body>
        <div class="position-relative image-side ">
          <p class=" text-white h2">
            {{ $t("dashboards.magic-is-in-the-details") }}
          </p>
          <p class="white mb-0">
            Please use this form to register. <br />If you are a member, please
            <router-link to="/user/login" class="white">login</router-link>.
          </p>
        </div>
        <div class="form-side">
          <router-link to="/"><span class="logo-single"/></router-link>
          <h6 class="mb-4">회원가입</h6>
          <b-form @submit.prevent="formSubmit">
            <b-form-group :label="$t('user.name')" class="has-float-label mb-4">
              <b-form-input
                type="text"
                v-model="$v.form.name.$model"
                :state="!$v.form.name.$error"
              />
              <b-form-invalid-feedback v-if="!$v.form.name.required"
                >Please enter your name</b-form-invalid-feedback
              >
            </b-form-group>
            <b-form-group
              :label="$t('user.email')"
              class="has-float-label mb-4"
            >
              <b-form-input
                type="text"
                v-model="$v.form.email.$model"
                :state="!$v.form.email.$error"
              />
              <b-form-invalid-feedback v-if="!$v.form.email.required"
                >Please enter your email address</b-form-invalid-feedback
              >
              <b-form-invalid-feedback v-else-if="!$v.form.email.email"
                >Please enter a valid email address</b-form-invalid-feedback
              >
              <b-form-invalid-feedback v-else-if="!$v.form.email.minLength"
                >Your email must be minimum 4
                characters</b-form-invalid-feedback
              >
            </b-form-group>
            <b-form-group
              :label="$t('user.password')"
              class="has-float-label mb-4"
            >
              <b-form-input
                type="password"
                v-model="$v.form.password.$model"
                :state="!$v.form.password.$error"
              />
              <b-form-invalid-feedback v-if="!$v.form.password.required"
                >Please enter your password</b-form-invalid-feedback
              >
              <b-form-invalid-feedback
                v-else-if="
                  !$v.form.password.minLength || !$v.form.password.maxLength
                "
                >Your password must be between 4 and 16
                characters</b-form-invalid-feedback
              >
            </b-form-group>
            <b-button
              type="submit"
              variant="primary"
              size="lg"
              :disabled="processing"
              :class="{
                'btn-multiple-state btn-shadow': true,
                'show-spinner': processing,
                'show-success': !processing && loginError === false,
                'show-fail': !processing && loginError
              }"
            >
              <span class="spinner d-inline-block">
                <span class="bounce1"></span>
                <span class="bounce2"></span>
                <span class="bounce3"></span>
              </span>
              <span class="icon success">
                <i class="simple-icon-check"></i>
              </span>
              <span class="icon fail">
                <i class="simple-icon-exclamation"></i>
              </span>
              <span class="label">{{ $t("user.register-button") }}</span>
            </b-button>
          </b-form>
        </div>
      </b-card>
    </b-colxx>
  </b-row>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
import api from "@/apis/axios.js";
import { adminRoot } from "../../constants/config";
import { validationMixin } from "vuelidate";
const {
  required,
  maxLength,
  minLength,
  email
} = require("vuelidate/lib/validators");

export default {
  data() {
    return {
      form: {
        name: "",
        email: "",
        password: ""
      }
    };
  },
  mixins: [validationMixin],
  validations: {
    form: {
      password: {
        required,
        maxLength: maxLength(16),
        minLength: minLength(4)
      },
      email: {
        required,
        email,
        minLength: minLength(4)
      },
      name: {
        required
      }
    }
  },
  computed: {
    ...mapGetters(["isLogin", "currentUser", "processing", "loginError"])
  },
  methods: {
    formSubmit() {
      this.$v.$touch();
      this.$v.form.$touch();
      api
        .post("/users/register", {
          userName: this.form.name,
          userEmail: this.form.email,
          userPassword: this.form.password,
          userClsf: "001"
        })
        .then(response => {
          if (response.data.result === "success") {
            // 회원가입 성공
            this.$router.push("/user/login");
          } else {
            // 회원가입 실패
            this.$notify(
              "error",
              "Register Error",
              "회원가입에 실패했습니다.",
              {
                duration: 3000,
                permanent: false
              }
            );
          }
        })
        .catch(error => {
          if (error.response.data.result === "duplicated") {
            this.$notify(
              "error",
              "Register Error",
              "이미 존재하는 이메일 입니다.",
              {
                duration: 3000,
                permanent: false
              }
            );
          }
        });
    }
  }
};
</script>
