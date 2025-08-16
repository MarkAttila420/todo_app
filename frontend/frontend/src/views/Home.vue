<template>
	<div class="home">
		<h1>Todos</h1>
		<TodoList />
	</div>
</template>

<script setup lang="ts">
import TodoList from '../components/TodoList.vue'
</script>

<style scoped>
.home {
	max-width: 700px;
	margin: 24px auto;
	padding: 0 12px;
}

h1 {
	text-align: center;
}
</style>
