# Configuration pour protéger la branche main

Cette configuration peut être appliquée via les paramètres GitHub :

## Instructions de protection de branche

1. Allez sur le dépôt GitHub
2. Allez dans Settings → Branches
3. Cliquez sur "Add rule"
4. Entrez le nom de branche: `main`
5. Activez les option suivantes:
   - ✅ Require a pull request before merging
   - ✅ Require status checks to pass before merging
     - Status check: "test" (du workflow GitHub Actions)
   - ✅ Include administrators
   - ✅ Restrict who can push to matching branches

## Configuration via GitHub CLI (gh)

```bash
gh repo edit --template "main" --branch-protection-rules "require-pull-request,require-status-checks:test"
```

## Configuration actuelle

Cette configuration garantit que:
- Aucun commit direct sur `main`
- Les tests doivent passer avant le merge (via GitHub Actions)
- Les administrateurs ne peuvent pas contourner ces règles
