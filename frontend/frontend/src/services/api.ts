import axios from 'axios';
import type { Todo } from '../types/todo';


const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/todos',
  headers: {
    'Content-Type': 'application/json',
  },
});

export async function fetchTodos(): Promise<Todo[]> {
  const response = await apiClient.get<Todo[]>('');
  return response.data;
}

export async function createTodo(todo: Todo): Promise<Todo> {
  const response = await apiClient.post<Todo>('', todo);
  return response.data;
}

export async function updateTodo(todo: Todo): Promise<Todo> {
  const response = await apiClient.put<Todo>(`/${todo.id}`, todo);
  return response.data;
}

export async function deleteTodo(id: number): Promise<void> {
  await apiClient.delete(`/${id}`);
}