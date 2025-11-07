class CustomNavbar extends HTMLElement {
    connectedCallback() {
        this.attachShadow({ mode: 'open' });
        this.shadowRoot.innerHTML = `
            <style>
                .navbar {
                    transition: all 0.3s ease;
                    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                }
                .profile-btn:hover {
                    transform: scale(1.1);
                }
            </style>
            <header class="navbar bg-blue-800 text-white p-4 fixed top-0 left-0 right-0 z-50 border-b border-gray-200">
                <div class="container mx-auto flex justify-between items-center">
                    <h1 class="text-xl md:text-2xl font-bold">Bem Vindo(a)</h1>
                    <button class="profile-btn bg-red-600 rounded-full p-2 transition-all duration-200">
                        <i data-feather="log-out"></i>
                    </button>
                </div>
            </header>
            <div class="h-16"></div> <!-- Spacer -->
        `;
    }
}
customElements.define('custom-navbar', CustomNavbar);