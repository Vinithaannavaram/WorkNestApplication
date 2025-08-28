function confirmDelete(entity, id) {
    const message = `Are you sure you want to delete this ${entity}?`;
    if (confirm(message)) {
        // Redirect to the appropriate controller with delete action and ID
        window.location.href = `${entity}Controller?action=delete&id=${id}`;
    }
}