export function getAliases(state) {
	return state.entities.aliases;
}

export function isModalOpen(state, modal) {
	return state.ui.modals[modal].open;
}
