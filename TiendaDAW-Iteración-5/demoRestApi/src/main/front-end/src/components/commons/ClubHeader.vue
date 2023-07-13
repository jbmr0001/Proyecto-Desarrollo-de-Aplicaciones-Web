<template>
  <header class="p-5 bg-light rounded-3">
    <h1>
      {{ text }}
    </h1>
  </header>
</template>

<script>
//Sample layout component
export default {
  props: [
    'text' //Component attribute (single binding)
  ]
}
</script>