<template>
<span class="message" title="tanxiang-music">
  <img src="https://www.tanxiangblog.com/icon/logo.png" alt="logo" id="logo">
  <slot>
      <span id="website-name">
        Music
    </span>
  </slot>
</span>
</template>

<script>
export default {
name: "WebsiteInfo"
}
</script>

<style scoped>
.message{
  padding: 10px;
  cursor: pointer;
  font-size: 17px;
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: center;
}

#logo{
  width: 60px;
  margin-right: 10px;
}

#website-name{
  font-size: 30px;
  font-weight: 700;
  color: red;
}
</style>