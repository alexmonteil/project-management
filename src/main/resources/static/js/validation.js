const onClickConfirm = (message) => {
    if (!confirm(message)) {
        return false;
    }
}
