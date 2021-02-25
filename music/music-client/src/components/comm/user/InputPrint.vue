<template>
  <div class="username input-user">
    <slot name="input">
      <label class="label-input">
        <input :type="input_type"
               :placeholder="input_placeholder"
               :id="input_id"
               :name="input_id"
               class="input"
               v-model.lazy.trim="data"
               :maxLength="input_maxLength"
               @blur="inputBlur"
               @focus="inputFocus"/>
      </label>
    </slot>
    <slot name="other"></slot>
  </div>
</template>

<script>
export default {
  name: "InputPrint",
  props: {
    input_placeholder: String,
    input_id: String,
    input_type: String,
    input_maxLength: String,
    input_errorMessage: String,
    type: {
      type: String,
      require: true
    },
  },
  data(){
    return {
      data: null
    }
  },
  methods: {
    inputBlur($event){
      if($event.currentTarget.value.toString().length === 0){
        $event.currentTarget.setAttribute("placeholder",this.input_errorMessage)
        $event.currentTarget.setAttribute("class","input input-error")
      }
      this.$emit('give',{
        type: this.type,
        code: this.data
      })
    },
    inputFocus($event){
        $event.currentTarget.setAttribute("placeholder",this.input_placeholder)
        $event.currentTarget.setAttribute("class","input")
    }
  }
}
</script>

<style scoped>
.input-user{
  width: 100%;
  height: 50px;
  display: flex;
  padding: 10px 0 0 0;
  justify-content: center;
  align-items: center;
  position: relative;
}

.input{
  width: 100%;
  height: 40px;
  border: none;
  outline: inherit;
  padding: 5px;
  font-size: 14px;
  border-bottom: 1px solid #d4d4d4;
}

.label-input{
  position: relative;
  width: 90%;
}

.input-error::placeholder{
  color: red;
}
.input-error::-moz-placeholder{
  color: red;
}
.input-error:-moz-placeholder{
  color: red;
}
.input-error::-ms-input-placeholder{
   color: red;
}

</style>
