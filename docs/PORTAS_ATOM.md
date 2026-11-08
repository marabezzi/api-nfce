# Registro de portas ATOM — 2026-11-07

Esta aplicação reserva portas **exclusivas no host** para coexistir com outros sistemas ATOM no mesmo computador. Portas **internas** de contêineres isolados podem se repetir.

## Este repositório

API de NFC-e/NF-e 9090 (SERVER_PORT pode sobrescrever); banco de dados não é publicado por este repositório. Reserve portas próprias se executar serviços auxiliares no host.

## Verificação no computador

```bash
docker ps --format 'table {{.Names}}\t{{.Ports}}'
sudo ss -ltnp
```

Não execute stacks simultâneos com mapeamentos coincidentes. Revise os arquivos `.env` locais: valores definidos neles prevalecem sobre padrões do repositório.

**HTTPS em produção:** apenas um gateway/proxy deve ocupar 80/443 no mesmo IP; mantenha os ingressos existentes até configurar um proxy compartilhado. Alterar portas públicas pode invalidar desafios ACME/Let's Encrypt e requer homologação antes do merge.
