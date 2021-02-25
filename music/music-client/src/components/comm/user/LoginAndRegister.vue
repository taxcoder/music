<template>
<div>
  <div class="cartoon">
    <a :href="address">
      <website-info>
        <template><span id="website-name">Music-WebSite</span></template>
      </website-info>
    </a>
  </div>

  <slot name="form-input"></slot>

</div>
</template>

<script>
import WebsiteInfo from "@/components/comm/header/WebsiteInfo";
export default {
  name: "LoginAndRegister",
  data(){
    return {
      address: '/index'
    }
  },
  components: {
    WebsiteInfo
  }
}
</script>

<style scoped>
a{
  text-decoration: none;
}

.cartoon{
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  margin-bottom: 10px;
}

.message{
  padding: 10px 0;
}

#website-name{
  font-size: 45px;
  font-weight: 700;
  color: red;
}
</style>