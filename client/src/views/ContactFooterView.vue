<template>
  <footer class="bg-dark text-white">
    <div class="container text-center">
      <div class="row mb-0">
        <!-- Contact us -->
        <div class="col mt-4">
          <h5 class="text-white">{{ $t('contact_tt') }}</h5>
          <ul class="list-unstyled">
            <li>{{ $t('email') }}: {{ $t('app_email') }}</li>
            <li>{{ $t('phone') }}: {{ $t('app_phone') }}</li>
            <li>{{ $t('address') }}: {{ $t('app_address') }}</li>
          </ul>
        </div>
        <!-- Follow us -->
        <div class="col mt-4">
          <h5 class="text-white">{{ $t('follow_tt') }}</h5>
          <ul class="list-unstyled">
            <li class="">
              <i-devicon:facebook class="mr-1p" />
              <a :href="$t('app_facebook')" class="text-decoration-none text-white">{{ $t('facebook_tt') }}</a>
            </li>
            <li class="">
              <i-devicon:twitter class="mr-1p" />
              <a :href="$t('app_twitter')" class="text-decoration-none text-white">{{ $t('twitter_tt') }}</a>
            </li>
            <li class="">
              <i-skill-icons:instagram class="mr-1p" />
              <a :href="$t('app_instagram')" class="text-decoration-none text-white">{{ $t('instagram_tt') }}</a>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <!-- Copyright -->
    <div class="text-center py-3 bg-dark text-white">
      <p>&copy; 2024 EventPlanner. All rights reserved.</p>
    </div>
  </footer>
</template>

<style scoped>
footer h5 {
  color: var(--color-primary);
  font-size: 1.2rem;
  font-weight: bold;
}

footer ul {
  list-style: none;
  padding-left: 0;
}

footer ul li {
  margin-bottom: 10px;
}

footer ul li a:hover {
  text-decoration: underline;
}

.bg-dark {
  background-color: var(--color-primary) !important;
}

.mr-1p {
  margin-right: 1%;
}
</style>
