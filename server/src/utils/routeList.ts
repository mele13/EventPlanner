import express from 'express';

// Print route list
export function printRoutes(routerName: string, router: express.Router) {
  const routes = router.stack
    .filter((r) => r.route && r.route.path)
    .map((r) => r.route.path)
  console.log(`${routerName}: ${routes.join(', ')}`)
}

// Usage: printRoutes("Users: ", router)
