const onClickConfirmDeletion = resource => {
    if (!confirm("Are you sure you want to delete this " + resource + " ?")) {
        return false;
    }
}