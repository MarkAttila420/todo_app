<template>
	<li class="todo-item">
		<label>
			<input type="checkbox" :checked="todo.completed" @change="toggle" />
			<span :class="{ completed: todo.completed }">{{ todo.title }}</span>
		</label>
		<button class="delete" @click="del">Delete</button>
	</li>
</template>

<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'
import type { Todo } from '../types/todo'

const props = defineProps<{ todo: Todo }>()
const emit = defineEmits<{
	(e: 'toggle', todo: Todo): void
	(e: 'delete', id: number | undefined): void
}>()

function toggle() {
	emit('toggle', props.todo)
}

function del() {
	emit('delete', props.todo.id)
}
</script>

<style scoped>
.todo-item {
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 8px 12px;
	border-bottom: 1px solid #eee;
}
.todo-item label {
	display: flex;
	gap: 8px;
	align-items: center;
}
.completed {
	text-decoration: line-through;
	opacity: 0.7;
}
.delete {
	background: transparent;
	border: 1px solid #e53935;
	color: #e53935;
	padding: 4px 8px;
	border-radius: 4px;
}
</style>
