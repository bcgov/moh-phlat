<template>
  <v-container class="text-center">
    <h1 class="my-6">{{ $t('trans.notFound.pageNotFound') }}</h1>
    <router-link :to="{ name: 'Home' }">
      <v-btn color="primary" size="large">
        <v-icon start icon="mdi-home"></v-icon>
        <span>{{ $t('trans.notFound.gotohome') }}</span>
      </v-btn>
    </router-link>
  </v-container>
</template>
