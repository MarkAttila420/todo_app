<template>
	<div class="todo-list">
		<div v-if="loading">Loading todos…</div>
		<div v-else-if="error" class="error">{{ error }}</div>
		<ul v-else>
			<li v-if="todos.length === 0">No todos yet.</li>
			<Todoitem
				v-for="todo in todos"
				:key="todo.id"
				:todo="todo"
				@toggle="onToggle"
				@delete="onDelete"
			/>
		</ul>
	</div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import type { Todo } from '../types/todo'
import { fetchTodos, updateTodo, deleteTodo } from '../services/api'
import Todoitem from './Todoitem.vue'

const todos = ref<Todo[]>([])
const loading = ref(false)
const error = ref<string | null>(null)

async function load() {
	loading.value = true
	error.value = null
	try {
		todos.value = await fetchTodos()
	} catch (err: any) {
		error.value = err?.message ?? 'Failed to load todos'
	} finally {
		loading.value = false
	}
}

onMounted(() => {
	load()
})

async function onToggle(t: Todo) {
	try {
		const updated = { ...t, completed: !t.completed }
		await updateTodo(updated)
		// update local state
		const idx = todos.value.findIndex(x => x.id === t.id)
		if (idx !== -1) todos.value[idx] = updated
	} catch (err) {
		// ignore for now or show toast
		console.error(err)
	}
}

async function onDelete(id: number | undefined) {
	if (id == null) return
	try {
		await deleteTodo(id)
		todos.value = todos.value.filter(t => t.id !== id)
	} catch (err) {
		console.error(err)
	}
}
</script>

<style scoped>
.todo-list ul {
	list-style: none;
	padding: 0;
}
.error {
	color: #b00020;
}
</style>
